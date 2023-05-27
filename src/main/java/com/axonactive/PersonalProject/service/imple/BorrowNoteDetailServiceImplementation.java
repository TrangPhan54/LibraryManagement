package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteDetailRepository;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import com.axonactive.PersonalProject.service.mapper.BorrowNoteDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowNoteDetailServiceImplementation implements BorrowNoteDetailService {
    private final BorrowNoteDetailRepository borrowNoteDetailRepository;
    private final BookRepository bookRepository;
    private final BorrowNoteRepository borrowNoteRepository;

    private final BorrowNoteDetailMapper borrowNoteDetailMapper;

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
    //1. Danh sách số lượng sách mà khách hàng cụ thể hiện tại đang mượn

    @Override
    public Long getNumberOfBookByCustomerId(Long customerId) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        Long numberOfBorrowedBooks = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getCustomerID() == customerId).count();
//                .mapToLong(brd -> brd.getQuantityOfBooks())
//                .sum();

        return numberOfBorrowedBooks;
    }
    //2. Tính số sách còn mượn sau khi trả của một khách hàng cụ thể

    @Override
    public Long customerReturnBook(Long customerId, Long numberOfBooksReturned) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        List<BorrowNoteDetail> tempList = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getCustomerID() == customerId)
                .collect(Collectors.toList());
//        for(int i = 0; i< tempList.size(); i++) {
//            BorrowNoteDetail borrowNoteDetail = borrowNoteDetailRepository.findById(tempList.get(i).getBorrowDetailID()).orElseThrow();
//            if(borrowNoteDetail.getBook().getBookID() == bookId) {
//                borrowNoteDetail.setQuantityOfBooks(borrowNoteDetail.getQuantityOfBooks() - numberOfBooksReturned);
//            }
//        }
        Long numberOfRemainingBooks = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getCustomerID() == customerId).count() - numberOfBooksReturned;

        tempList.forEach(System.out::println);
        return numberOfRemainingBooks;

    }
    //3. Liệt kê các cuốn sách mà 1 khách hàng vẫn còn mượn sau khi trả trước những cuốn đọc rồi.

    @Override
    public List<String> nameOfBookRemaining(Long customerId, Long bookId) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        List<BorrowNoteDetail> bookListOfCustomer = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getCustomerID() == customerId)
                .collect(Collectors.toList());
//        for (BorrowNoteDetail borrowNoteDetail : bookListOfCustomer){
//            if (borrowNoteDetail.getBook().getBookID() == bookId){
//                borrowNoteDetailRepository.deleteAllById();
//            }
//        }
        for (int i = 0; i < bookListOfCustomer.size(); i++) {
            if (bookListOfCustomer.get(i).getBook().getBookID() == bookId) {
                borrowNoteDetailRepository.deleteById(bookListOfCustomer.get(i).getBorrowDetailID());
            }
        }
        borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        return borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getCustomerID() == customerId)
                .map(brd -> brd.getBook().getBookName())
                .collect(Collectors.toList());
    }





}
