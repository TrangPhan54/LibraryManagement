package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.*;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.finecalculator.FineCalculator;
import com.axonactive.PersonalProject.finecalculator.PaymentGateway;
import com.axonactive.PersonalProject.finecalculator.PaymentGatewayAdapter;
import com.axonactive.PersonalProject.repository.*;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.BookAnalyticForAmountOfTimeDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.CustomerWithNumberOfPhysicalCopiesBorrow;
import com.axonactive.PersonalProject.service.dto.customedDto.FineFeeForCustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.ReturnBookByCustomerDto;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import com.axonactive.PersonalProject.service.mapper.BorrowNoteDetailMapper;
import com.axonactive.PersonalProject.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BorrowNoteDetailServiceImplementation implements BorrowNoteDetailService {
    private final BorrowNoteDetailRepository borrowNoteDetailRepository;
    private final PhysicalBookRepository physicalBookRepository;
    private final BorrowNoteRepository borrowNoteRepository;

    private final BorrowNoteDetailMapper borrowNoteDetailMapper;

    private final CustomerRepository customerRepository;

    private final BookMapper bookMapper;
    private final CustomerMapper customerMapper;

    @Override
    public List<BorrowNoteDetailDTO> getAllBorrowNoteDetail() {
        List<BorrowNoteDetail> borrowNoteDetails = borrowNoteDetailRepository.findAll();
        return borrowNoteDetailMapper.toDtos(borrowNoteDetails);

    }

    public BorrowNoteDetailDTO createBorrowNoteDetail(CreateBorrowNoteDetailDTO createBorrowNoteDetailDTO) {
        BorrowNoteDetail borrowNoteDetail = new BorrowNoteDetail();
        PhysicalBook physicalBook = physicalBookRepository.findById(createBorrowNoteDetailDTO.getPhysicalBookID()).orElseThrow(LibraryException::PhysicalBookNotFound);
        BorrowNote note = borrowNoteRepository.findById(createBorrowNoteDetailDTO.getBorrowNoteID()).orElseThrow(LibraryException::BorrowNoteNotFound);
        borrowNoteDetail.setPhysicalBook(physicalBook);
        borrowNoteDetail.setBorrowNote(note);
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
        return borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId).count();
    }
    //2. Tính số sách còn mượn sau khi trả của một khách hàng cụ thể

    @Override
    public Long customerReturnBook(Long customerId, Long numberOfBooksReturned) {
        log.info("request the number of return books by customer id {}", customerId);
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll().stream()
                .filter(brd -> Objects.equals(brd.getBorrowNote().getCustomer().getId(), customerId))
                .collect(Collectors.toList());
        Long numberOfRemainingBooks = borrowNoteDetailList.stream()
                .filter(brd -> Objects.equals(brd.getBorrowNote().getCustomer().getId(), customerId)).count() - numberOfBooksReturned;

        borrowNoteDetailList.forEach(System.out::println);
        return numberOfRemainingBooks;

    }

    //3. Liệt kê tên các cuốn sách mà 1 khách hàng vẫn còn mượn sau khi trả trước những cuốn đọc rồi.
    @Override
    public List<String> nameOfBookRemaining(Long customerId, List<Long> physicalBookIds) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        List<BorrowNoteDetail> bookListOfCustomer = borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId)
                .collect(Collectors.toList());
        for (int i = 0; i < bookListOfCustomer.size(); i++) {
            for (Long j : physicalBookIds) {
                if (Objects.equals(bookListOfCustomer.get(i).getPhysicalBook().getId(), j)) {
                    borrowNoteDetailRepository.deleteById(bookListOfCustomer.get(i).getId());
                }
            }
        }
        borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        return borrowNoteDetailList.stream()
                .filter(brd -> brd.getBorrowNote().getCustomer().getId() == customerId)
                .map(brd -> brd.getPhysicalBook().getBook().getName())
                .collect(Collectors.toList());
    }

    public List<BorrowNoteDetail> getBookListOfACustomer(ReturnBookByCustomerDto returnBookByCustomerDto) {
//        return borrowNoteDetailRepository.findAll().stream().filter(brd -> Objects.equals(brd.getBorrowNote().getCustomer().getId(), returnBookByCustomerDto.getCustomerId()))
//                .collect(Collectors.toList());
        return borrowNoteDetailRepository.findByBorrowNoteCustomerId(returnBookByCustomerDto.getCustomerId());
    }

    public List<Long> getBookListIdOfACustomer(ReturnBookByCustomerDto returnBookByCustomerDto) {
        return borrowNoteDetailRepository.findAll().stream().filter(brd -> Objects.equals(brd.getBorrowNote().getCustomer().getId(), returnBookByCustomerDto.getCustomerId()))
                .map(BorrowNoteDetail::getPhysicalBook).map(PhysicalBook::getId)
                .collect(Collectors.toList());
    }

    public List<BorrowNoteDetail> returnBook(ReturnBookByCustomerDto returnBookByCustomerDto) {
        List<BorrowNoteDetail> bookListOfCustomer = getBookListOfACustomer(returnBookByCustomerDto);
        List<BorrowNoteDetail> bookListReturnOfCustomer = new ArrayList<>();
        for (BorrowNoteDetail noteDetail : bookListOfCustomer) {
            Long physicalBookId = noteDetail.getPhysicalBook().getId();
            if (returnBookByCustomerDto.getPhysicalBookIds().contains(physicalBookId)) {
                noteDetail.setReturnDate(LocalDate.now());
                noteDetail.setCondition(Condition.NORMAL);
                bookListReturnOfCustomer.add(noteDetail);
            }
        }
        return bookListReturnOfCustomer;
    }

    // 5. Dịch vụ trả sách. Nếu trả trễ từ 20 cuốn trở lên thì tiến hành khóa tài khoản
    @Override
    public CustomerDTO banAccountForReturningBookLate(ReturnBookByCustomerDto returnBookByCustomerDto) {
        List<BorrowNoteDetail> bookListReturnOfCustomer = returnBook(returnBookByCustomerDto);
        Customer customer = customerRepository.findById(returnBookByCustomerDto.getCustomerId()).orElseThrow(LibraryException::CustomerNotFound);
        for (BorrowNoteDetail noteDetail : bookListReturnOfCustomer) {
            LocalDate dueDate = noteDetail.getBorrowNote().getDueDate();
            if (LocalDate.now().isAfter(dueDate)) {
                if (customer.getNumberOfTimeReturnLate() < 20) {
                    customer.setNumberOfTimeReturnLate(customer.getNumberOfTimeReturnLate() + 1);
                } else {
                    customer.setActive(false);
                }
            }
        }
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    //5. Tính tiền phạt nếu sách trả trễ so với hạn đã ghi trong phiếu mượn
    @Override
    public FineFeeForCustomerDTO fineFeeForReturningBookLate(ReturnBookByCustomerDto returnBookByCustomerDto) {
        List<BorrowNoteDetail> bookListReturnOfCustomer = returnBook(returnBookByCustomerDto);
        double totalFee = 0;
        for (BorrowNoteDetail noteDetail : bookListReturnOfCustomer) {
            LocalDate dueDate = noteDetail.getBorrowNote().getDueDate();
            FineCalculator fineCalculator = new FineCalculator(); //using design pattern: Adapter
            PaymentGateway paymentGateway = new PaymentGatewayAdapter(fineCalculator);
            if (LocalDate.now().isAfter(dueDate)) {
                Long overdueDays = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
                noteDetail.setFineFee(paymentGateway.processPayment(overdueDays));
                totalFee += noteDetail.getFineFee();
            }
//            totalFee += noteDetail.getFineFee();
        }
        Customer customer = customerRepository.findById(returnBookByCustomerDto.getCustomerId()).orElseThrow(LibraryException::CustomerNotFound);
        FineFeeForCustomerDTO fineFeeForCustomerDTO = new FineFeeForCustomerDTO();
        fineFeeForCustomerDTO.setFirstName(customer.getFirstName());
        fineFeeForCustomerDTO.setLastName(customer.getLastName());
        fineFeeForCustomerDTO.setFineFee(totalFee);
        return fineFeeForCustomerDTO;
    }


    public String getBookNameByBookId(Long bookId) {
        Optional<BorrowNoteDetail> borrowNoteDetailListOfCustomer = borrowNoteDetailRepository.findAll().stream()
                .filter(brd -> Objects.equals(brd.getPhysicalBook().getBook().getId(), bookId)).findFirst();
        List<Book> bookOfCustomer = borrowNoteDetailListOfCustomer
                .stream().map(BorrowNoteDetail::getPhysicalBook).map(PhysicalBook::getBook)
                .collect(Collectors.toList());
        return bookOfCustomer.stream()
                .map(Book::getName)
                .collect(Collectors.joining(","));
    }

    //6. Thống kê tiêu đề sách được mượn nhiều nhất trong một khoảng thời gian nhất đinh
    @Override
    public List<BookAnalyticForAmountOfTimeDTO> getMaxBorrowBook(LocalDate date1, LocalDate date2) {
        List<BorrowNoteDetail> brdListBetweenDates = borrowNoteDetailRepository.findByBorrowNoteBorrowDateBetween(date1, date2);
        List<Book> bookList = brdListBetweenDates.stream().map(BorrowNoteDetail::getPhysicalBook).map(PhysicalBook::getBook).collect(Collectors.toList());
        Map<Book, Long> booksWithPhysicalCopiedBorrowed = new HashMap<>();
        for (Book book : bookList) {
            booksWithPhysicalCopiedBorrowed.put(book, 0L);
        }
        for (Map.Entry<Book, Long> entry : booksWithPhysicalCopiedBorrowed.entrySet()) {
            Book key = entry.getKey();
            Long value = entry.getValue();
            for (Book book : bookList) {
                if (Objects.equals(book.getId(), key.getId())) {
                    value++;
                    entry.setValue(value);
                }
            }
        }
        Map<Book, Long> result = booksWithPhysicalCopiedBorrowed.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        List<BookAnalyticForAmountOfTimeDTO> bookAnalyticForAmountOfTimeDTOS = new ArrayList<>();
        for (Map.Entry<Book, Long> entry : result.entrySet()) {
            Book key = entry.getKey();
            Long value = entry.getValue();
            BookAnalyticForAmountOfTimeDTO bookAnalyticForAmountOfTimeDTO = new BookAnalyticForAmountOfTimeDTO();
            bookAnalyticForAmountOfTimeDTO.setBookID(key.getId());
            bookAnalyticForAmountOfTimeDTO.setBookTitle(key.getName());
            bookAnalyticForAmountOfTimeDTO.setNumberOfPhysicalBookCopies(value);
            bookAnalyticForAmountOfTimeDTOS.add(bookAnalyticForAmountOfTimeDTO);

        }
        return bookAnalyticForAmountOfTimeDTOS;
    }
    //7. Thống kê những khách hàng mượn nhiều nhất trong một khoảng thời gian nhất định

    @Override
    public List<CustomerWithNumberOfPhysicalCopiesBorrow> getMaxCustomer(LocalDate date1, LocalDate date2) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findByBorrowNoteBorrowDateBetween(date1, date2);
        List<Customer> customers = borrowNoteDetailList.stream().map(BorrowNoteDetail::getBorrowNote).map(BorrowNote::getCustomer).collect(Collectors.toList());
        Map<Customer, Long> customerWithNumberOfPhysicalCopiesBorrow = new HashMap<>();
        for (Customer customer : customers) {
            customerWithNumberOfPhysicalCopiesBorrow.put(customer, 0L);
        }
        for (Map.Entry<Customer, Long> entry : customerWithNumberOfPhysicalCopiesBorrow.entrySet()) {
            Customer key = entry.getKey();
            Long value = entry.getValue();
            for (Customer customer : customers) {
                if (Objects.equals(customer.getId(), key.getId())) {
                    value++;
                    entry.setValue(value);
                }
            }
        }
        Map<Customer, Long> result = customerWithNumberOfPhysicalCopiesBorrow.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new));

        List<CustomerWithNumberOfPhysicalCopiesBorrow> customerWithNumberOfPhysicalCopiesBorrowList = new ArrayList<>();

        for (Map.Entry<Customer, Long> entry : result.entrySet()) {
            Customer key = entry.getKey();
            Long value = entry.getValue();
            CustomerWithNumberOfPhysicalCopiesBorrow customerWithNumberOfPhysicalCopiesBorrow1 = new CustomerWithNumberOfPhysicalCopiesBorrow();
            customerWithNumberOfPhysicalCopiesBorrow1.setCustomerId(key.getId());
            customerWithNumberOfPhysicalCopiesBorrow1.setLastName(key.getLastName());
            customerWithNumberOfPhysicalCopiesBorrow1.setFirstName(key.getFirstName());
            customerWithNumberOfPhysicalCopiesBorrow1.setNumberOfPhysicalCopiesBorrow(value);
            customerWithNumberOfPhysicalCopiesBorrowList.add(customerWithNumberOfPhysicalCopiesBorrow1);
        }
        return customerWithNumberOfPhysicalCopiesBorrowList;
    }
}
