package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteDetailRepository;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import com.axonactive.PersonalProject.service.mapper.BorrowNoteDetailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BorrowNoteDetailServiceImplementation implements BorrowNoteDetailService {
    private final BorrowNoteDetailRepository borrowNoteDetailRepository;
    private final BookRepository bookRepository;
    private final BorrowNoteRepository borrowNoteRepository;

    private final BorrowNoteDetailMapper borrowNoteDetailMapper;

    private final CustomerRepository customerRepository;

    private final BookMapper bookMapper;

    @Override
    public List<BorrowNoteDetailDTO> getAllBorrowNoteDetail() {
        List<BorrowNoteDetail> borrowNoteDetails = borrowNoteDetailRepository.findAll();
        return borrowNoteDetailMapper.toDtos(borrowNoteDetails);

    }

    @Override
    public BorrowNoteDetailDTO createBorrowNoteDetail(BorrowNoteDetailDTO borrowNoteDetailDTO, Long bookID, Long orderID) {
        BorrowNoteDetail borrowNoteDetail = new BorrowNoteDetail();
        Book book = bookRepository.findById(bookID).orElseThrow(LibraryException::BookNotFound);
        BorrowNote order = borrowNoteRepository.findById(orderID).orElseThrow(LibraryException::BorrowNoteNotFound);
//        Long n= borrowNoteDetailDTO.getQuantityOfBooks();
//        borrowNoteDetail.setQuantityOfBooks(n);
        borrowNoteDetail.setBook(book);
        borrowNoteDetail.setBorrowNote(order);
        borrowNoteDetail = borrowNoteDetailRepository.save(borrowNoteDetail);


        return borrowNoteDetailMapper.toDto(borrowNoteDetail);
    }


    @Override
    public void deleteBorrowNoteDetailByID(Long borrowNoteDetailID) {
        BorrowNoteDetail borrowNoteDetail = borrowNoteDetailRepository.findById(borrowNoteDetailID).orElseThrow(LibraryException::BorrowNoteDetailNotFound);
        borrowNoteDetailRepository.delete(borrowNoteDetail);
    }

    @Override
    public BorrowNoteDetailDTO getBorrowNoteDetailId(Long borrowNoteDetailId) {
        return borrowNoteDetailMapper.toDto(borrowNoteDetailRepository.findById(borrowNoteDetailId).orElseThrow(LibraryException::BorrowNoteDetailNotFound));
    }
    //1. Danh sách số lượng sách mà khách hàng cụ thể hiện tại đang mượn

    @Override
    public Long getNumberOfBookByCustomerId(Long customerId) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        Long numberOfBorrowedBooks = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId).count();

        return numberOfBorrowedBooks;
    }
    //2. Tính số sách còn mượn sau khi trả của một khách hàng cụ thể

    @Override
    public Long customerReturnBook(Long customerId, Long numberOfBooksReturned) {
        log.info("request the number of return books by customer id {}",customerId);
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        List<BorrowNoteDetail> tempList = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId)
                .collect(Collectors.toList());
        Long numberOfRemainingBooks = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId).count() - numberOfBooksReturned;

        tempList.forEach(System.out::println);
        return numberOfRemainingBooks;

    }
    //3. Liệt kê các cuốn sách mà 1 khách hàng vẫn còn mượn sau khi trả trước những cuốn đọc rồi.

    @Override
    public List<String> nameOfBookRemaining(Long customerId, Long bookId) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        List<BorrowNoteDetail> bookListOfCustomer = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId)
                .collect(Collectors.toList());

        for (int i = 0; i < bookListOfCustomer.size(); i++) {
            if (bookListOfCustomer.get(i).getBook().getId() == bookId) {
                borrowNoteDetailRepository.deleteById(bookListOfCustomer.get(i).getId());
            }
        }
        borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        return borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId)
                .map(brd -> brd.getBook().getName())
                .collect(Collectors.toList());
    }


    @Override
    public void returnBookByCustomer(Long customerId, Long bookId) {
        List<BorrowNoteDetail> borrowNoteDetailListOfCustomer = borrowNoteDetailRepository.findAll().stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId)
                .collect(Collectors.toList());
//        List<Book> bookOfCustomer = borrowNoteDetailListOfCustomer
//                .stream().map(BorrowNoteDetail::getBook)
//                .collect(Collectors.toList());
        Customer customer = customerRepository.findById(customerId).orElseThrow(LibraryException::CustomerNotFound);

        for (int i = 0; i < borrowNoteDetailListOfCustomer.size(); i++) {
            if (borrowNoteDetailListOfCustomer.get(i).getBook().getId() == bookId) {
                if (LocalDate.now().isAfter(borrowNoteDetailListOfCustomer.get(i).getBorrowNote().getDueDate())) {
                    if (customer.getNumberOfTimeReturnLate() < 5) {
                        customer.setNumberOfTimeReturnLate(customer.getNumberOfTimeReturnLate() + 1);
                        if (customer.getNumberOfTimeReturnLate() == 5) {
                            customer.setActive(false);
                        }
                    } else {
                        System.out.println("Customer is banned because you have exceeded 5 overdue returns.");
                    }
                }
                System.out.println(customer.getNumberOfTimeReturnLate());
            }
        }
//
    }

    @Override
    public Long countNumberMaxBorrowBook(Long bookId) {
        return borrowNoteDetailRepository.countNumberMaxBorrowBook(bookId);
    }

    @Override
    public String getBookNameByBoodId(Long bookId) {
        Optional<BorrowNoteDetail> borrowNoteDetailListOfCustomer = borrowNoteDetailRepository.findAll().stream()
                .filter(brd -> brd.getBook().getId() == bookId).findFirst();
        List<Book> bookOfCustomer = borrowNoteDetailListOfCustomer
                .stream().map(BorrowNoteDetail::getBook)
                .collect(Collectors.toList());
        String bookName = bookOfCustomer.stream()
                .map(Book::getName)
                .collect(Collectors.joining(","));
        return bookName;
    }
    // Tim id nhung sach duoc muon nhieu nhat
    @Override
    public List<Long> maxBorrowBook() {
        return borrowNoteDetailRepository.maxBorrowBook();
    }
    // Tim ten nhung cuon sach duoc muon nhieu nhat

    @Override
    public List<Book> nameOfMaxBorrowBook() {
        return borrowNoteDetailRepository.nameOfMaxBorrowBook(maxBorrowBook());
    }
    // Tim id nhung sach duoc muon it nhat
    @Override
    public List<Long> minBorrowBook() {
        return borrowNoteDetailRepository.minBorrowBook();
    }
    // Tim ten nhung cuon sach duoc muon it nhat
    @Override
    public List<Book> nameOfMinBorrowBook() {
        return borrowNoteDetailRepository.nameOfMinBorrowBook(minBorrowBook());
    }

}
