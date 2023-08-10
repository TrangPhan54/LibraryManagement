package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.*;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.finecalculator.FineCalculator;
import com.axonactive.PersonalProject.finecalculator.PaymentGateway;
import com.axonactive.PersonalProject.finecalculator.PaymentGatewayAdapter;
import com.axonactive.PersonalProject.repository.*;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.*;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import com.axonactive.PersonalProject.service.mapper.BorrowNoteDetailMapper;
import com.axonactive.PersonalProject.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
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
    private final EntityManager entityManager;
    private final BookMapper bookMapper;
    private final CustomerMapper customerMapper;
    private static final Double LIMITATION_OVERDUE_TIMES = 20.0;
    private static final Long LIMITATION_OVERDUE_DAYS = 100L;
    FineCalculator fineCalculator = new FineCalculator(); // create object of service class
    PaymentGateway paymentGateway = new PaymentGatewayAdapter(fineCalculator); //PaymentGatewayAdapter class wraps an instance of FineCalculator and implements the PaymentGateway interface

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

    //1. Count number of borrowing books of a customer

    @Override
    public Long getNumberOfBookByCustomerId(Long customerId) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findAll();
        return borrowNoteDetailList.stream()
                .filter(brd -> Objects.equals(brd.getBorrowNote().getCustomer().getId(), customerId)).count();
    }

    public List<BorrowNoteDetail> getBookListOfACustomer(Long customerID) {
        return borrowNoteDetailRepository.findByBorrowNoteCustomerId(customerID);
    }

    // Get list of borrow note detail of a customer for function return book and lost book by using criteria builder
    public List<BorrowNoteDetail> getBookListOfACustomer1(Long customerID) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowNoteDetail> query = criteriaBuilder.createQuery(BorrowNoteDetail.class);
        Root<BorrowNoteDetail> root = query.from(BorrowNoteDetail.class);
        javax.persistence.criteria.Predicate condition = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("borrowNote").get("customer").get("id"), customerID)
        );
        query.select(root).where(condition);
        return entityManager.createQuery(query).getResultList();
    }

    public List<BorrowNoteDetailDTO> getBookListOfACustomer2(Long customerID) {
        return borrowNoteDetailMapper.toDtos(borrowNoteDetailRepository.findByBorrowNoteCustomerId(customerID));
    }

    public List<BorrowNoteDetailDTO> getBorowNoteDetailByBorrowNoteID(Long borrowID) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowNoteDetail> query = criteriaBuilder.createQuery(BorrowNoteDetail.class);
        Root<BorrowNoteDetail> root = query.from(BorrowNoteDetail.class);
        javax.persistence.criteria.Predicate condition = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("borrowNote").get("id"), borrowID)
        );
        query.select(root).where(condition);
        List<BorrowNoteDetail> books = entityManager.createQuery(query).getResultList();
        return borrowNoteDetailMapper.toDtos(books);

    }
    // 2. Returning book service (customer return book ontime)

    public List<BorrowNoteDetail> returnBook(ReturnBookByCustomerDTO returnBookByCustomerDTO) {
        List<BorrowNoteDetail> bookListOfCustomer = getBookListOfACustomer1(returnBookByCustomerDTO.getCustomerId());
        List<BorrowNoteDetail> bookListReturnOfCustomer = new ArrayList<>();
        for (BorrowNoteDetail noteDetail : bookListOfCustomer) {
            Long physicalBookId = noteDetail.getPhysicalBook().getId();
            if (returnBookByCustomerDTO.getPhysicalBookIds().contains(physicalBookId)) {
                noteDetail.setReturnDate(LocalDate.now());
                noteDetail.setCondition(Condition.NORMAL);
                bookListReturnOfCustomer.add(noteDetail);
            }
        }
        return bookListReturnOfCustomer;
    }

    // 3. Returning book service (customer lost book)
    public FineFeeForCustomerDTO feeForLostBook(ReturnBookByCustomerDTO returnBookByCustomerDTO) {
        double totalFee = returnConditionLostBook(returnBookByCustomerDTO);
        Customer customer = customerRepository.findById(returnBookByCustomerDTO.getCustomerId()).orElseThrow(LibraryException::CustomerNotFound);
        FineFeeForCustomerDTO fineFeeForCustomerDTO = new FineFeeForCustomerDTO();
        fineFeeForCustomerDTO.setFirstName(customer.getFirstName());
        fineFeeForCustomerDTO.setLastName(customer.getLastName());
        fineFeeForCustomerDTO.setFineFee(totalFee);
        return fineFeeForCustomerDTO;
    }

    private double returnConditionLostBook(ReturnBookByCustomerDTO returnBookByCustomerDTO) {
        List<BorrowNoteDetail> borrowNoteDetailListOfCustomer = getBookListOfACustomer1(returnBookByCustomerDTO.getCustomerId());
        double totalFee = 0;
        for (BorrowNoteDetail borrowNoteDetail : borrowNoteDetailListOfCustomer) {
            Long physicalBookId = borrowNoteDetail.getPhysicalBook().getId();
            if (returnBookByCustomerDTO.getPhysicalBookIds().contains(physicalBookId)) {
                PhysicalBook physicalBook = physicalBookRepository.findById(physicalBookId).get();
                borrowNoteDetail.setFineFee(paymentGateway.processFineBookLost(physicalBook.getImportPrice()));
                borrowNoteDetail.setReturnDate(LocalDate.now());
                borrowNoteDetail.setCondition(Condition.LOST);
                totalFee += borrowNoteDetail.getFineFee();
            }
        }
        return totalFee;
    }

    // 4. Returning book service. If a customer return book late for 20 times, customer cannot borrow book in library anymore
    @Override
    public CustomerDTO banAccountForReturningBookLate1(ReturnBookByCustomerDTO returnBookByCustomerDto) {
        List<BorrowNoteDetail> bookListReturnOfCustomer = returnBook(returnBookByCustomerDto);
        Customer customer = customerRepository.findById(returnBookByCustomerDto.getCustomerId()).orElseThrow(LibraryException::CustomerNotFound);
        for (BorrowNoteDetail noteDetail : bookListReturnOfCustomer) {
            LocalDate dueDate = noteDetail.getBorrowNote().getDueDate();
            if (isOverDueDate(dueDate) && numberOfTimeReturnLateOverLimitation(customer)) {
                customer.setNumberOfTimeReturnLate(customer.getNumberOfTimeReturnLate() + 1);
                if (!numberOfTimeReturnLateOverLimitation(customer)) {
                    customer.setActive(false);
                }
            }
        }
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO banAccountForReturningBookLate(ReturnBookByCustomerDTO returnBookByCustomerDto) {
        List<BorrowNoteDetail> bookListReturnOfCustomer = returnBook(returnBookByCustomerDto);
        Customer customer = customerRepository.findById(returnBookByCustomerDto.getCustomerId()).orElseThrow(LibraryException::CustomerNotFound);
        for (BorrowNoteDetail noteDetail : bookListReturnOfCustomer) {
            LocalDate dueDate = noteDetail.getBorrowNote().getDueDate();
            Predicate<LocalDate> testOverdue = x -> x.isBefore(LocalDate.now());
            if (testOverdue.test(dueDate)) {
                Predicate<Long> numberOfTimeReturnLate = x -> x < LIMITATION_OVERDUE_TIMES;
                if (numberOfTimeReturnLate.test(customer.getNumberOfTimeReturnLate())) {
                    customer.setNumberOfTimeReturnLate(customer.getNumberOfTimeReturnLate() + 1);
                    if (customer.getNumberOfTimeReturnLate() >= LIMITATION_OVERDUE_TIMES) {
                        customer.setActive(false);
                    }
                }
            }
        }
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    // method to define if the books are returned late or not
    private boolean isOverDueDate(LocalDate returnDate) {
        Predicate<LocalDate> testOverDue = x -> x.isBefore(LocalDate.now());
        return testOverDue.test(returnDate);
    }

    // method to define if customer return book late over limitation
    private boolean numberOfTimeReturnLateOverLimitation(Customer customer) {
        Predicate<Long> numberOfTimeReturnLate = x -> x < LIMITATION_OVERDUE_TIMES;
        return numberOfTimeReturnLate.test(customer.getNumberOfTimeReturnLate());
    }

    //5. Returning book service. (using Adapter design pattern). Customer have to pay fee and the fee base on number of overdue days
    @Override
    public FineFeeForCustomerDTO customerWithfineFeeForReturningBookLate(ReturnBookByCustomerDTO returnBookByCustomerDTO) {
        double totalFee = fineFeeForReturningBookLate(returnBookByCustomerDTO);
        Customer customer = customerRepository.findById(returnBookByCustomerDTO.getCustomerId()).orElseThrow(LibraryException::CustomerNotFound);
        FineFeeForCustomerDTO fineFeeForCustomerDTO = new FineFeeForCustomerDTO();
        fineFeeForCustomerDTO.setFirstName(customer.getFirstName());
        fineFeeForCustomerDTO.setLastName(customer.getLastName());
        fineFeeForCustomerDTO.setFineFee(totalFee);
        return fineFeeForCustomerDTO;
    }

    private double fineFeeForReturningBookLate(ReturnBookByCustomerDTO returnBookByCustomerDTO) {
        List<BorrowNoteDetail> bookListReturnOfCustomer = returnBook(returnBookByCustomerDTO);
        double totalFee = 0;
        for (BorrowNoteDetail borrowNoteDetail : bookListReturnOfCustomer) {
            LocalDate dueDate = borrowNoteDetail.getBorrowNote().getDueDate();
            if (isOverDueDate(dueDate)) {
                Long overDueDays = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
                borrowNoteDetail.setFineFee(paymentGateway.processPayment(overDueDays));
                totalFee += borrowNoteDetail.getFineFee();
            }
        }
        return totalFee;
    }

    private List<Book> getBookBorrowListBaseOnAmountOfTime(LocalDate date1, LocalDate date2) {
        List<BorrowNoteDetail> borrowListBaseOnAmountOfTime = borrowNoteDetailRepository.findByBorrowNoteBorrowDateBetween(date1, date2);
        return borrowListBaseOnAmountOfTime.stream()
                .map(BorrowNoteDetail::getPhysicalBook)
                .map(PhysicalBook::getBook)
                .collect(Collectors.toList());
    }

    private Map<Book, Long> getBooksWithPhysicalCopiedBorrowed(LocalDate date1, LocalDate date2) {
        List<Book> bookList = getBookBorrowListBaseOnAmountOfTime(date1, date2);
        Map<Book, Long> booksWithPhysicalCopiedBorrowed = new HashMap<>();
        for (Book book : bookList) {
            booksWithPhysicalCopiedBorrowed.put(book, 0L);
        }
        for (Book book : bookList) {
            booksWithPhysicalCopiedBorrowed.merge(book, 1L, Long::sum);
        }
        return booksWithPhysicalCopiedBorrowed.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new));
    }

    //6. Book statistics for an amount of time
    @Override
    public List<BookAnalyticForAmountOfTimeDTO> getMaxBorrowBook(LocalDate date1, LocalDate date2) {
        Map<Book, Long> result = getBooksWithPhysicalCopiedBorrowed(date1, date2);
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

    // get list of customers borrow books for an amount of time
    private List<Customer> getCustomerBorrowListBaseOnAmountOfTime(LocalDate date1, LocalDate date2) {
        List<BorrowNoteDetail> borrowNoteDetailList = borrowNoteDetailRepository.findByBorrowNoteBorrowDateBetween(date1, date2);
        return borrowNoteDetailList.stream()
                .map(BorrowNoteDetail::getBorrowNote)
                .map(BorrowNote::getCustomer)
                .collect(Collectors.toList());
    }

    // get customer with number of physical book copies borrow
    private Map<Customer, Long> getCustomerWithNumberOfPhysicalCopiesBorrow(LocalDate date1, LocalDate date2) {
        Map<Customer, Long> customerWithNumberOfPhysicalCopiesBorrow = new HashMap<>();
        List<Customer> customerList = getCustomerBorrowListBaseOnAmountOfTime(date1, date2);
        for (Customer customer : customerList) {
            customerWithNumberOfPhysicalCopiesBorrow.put(customer, 0L);
        }
        for (Customer customer : customerList) {
            customerWithNumberOfPhysicalCopiesBorrow.merge(customer, 1L, Long::sum);
        }
        return customerWithNumberOfPhysicalCopiesBorrow.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new));
    }

    // Customer statistics for an amount of time
    @Override
    public List<CustomerWithNumberOfPhysicalCopiesBorrowDTO> getMaxCustomer(LocalDate date1, LocalDate date2) {
        Map<Customer, Long> result = getCustomerWithNumberOfPhysicalCopiesBorrow(date1, date2);
        List<CustomerWithNumberOfPhysicalCopiesBorrowDTO> customerWithNumberOfPhysicalCopiesBorrowDTOList = new ArrayList<>();
        for (Map.Entry<Customer, Long> entry : result.entrySet()) {
            Customer key = entry.getKey();
            Long value = entry.getValue();
            CustomerWithNumberOfPhysicalCopiesBorrowDTO customerWithNumberOfPhysicalCopiesBorrowDTO1 = new CustomerWithNumberOfPhysicalCopiesBorrowDTO();
            customerWithNumberOfPhysicalCopiesBorrowDTO1.setCustomerId(key.getId());
            customerWithNumberOfPhysicalCopiesBorrowDTO1.setLastName(key.getLastName());
            customerWithNumberOfPhysicalCopiesBorrowDTO1.setFirstName(key.getFirstName());
            customerWithNumberOfPhysicalCopiesBorrowDTO1.setNumberOfPhysicalCopiesBorrow(value);
            customerWithNumberOfPhysicalCopiesBorrowDTOList.add(customerWithNumberOfPhysicalCopiesBorrowDTO1);
        }
        return customerWithNumberOfPhysicalCopiesBorrowDTOList;
    }

    // get list of customer still borrow book to contact
    public List<BorrowNoteDetailDTO> getListOfCustomerStillBorrowBook2() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowNoteDetail> query = criteriaBuilder.createQuery(BorrowNoteDetail.class);
        Root<BorrowNoteDetail> root = query.from(BorrowNoteDetail.class);
        javax.persistence.criteria.Predicate condition = criteriaBuilder.and(
                criteriaBuilder.isNull(root.get("returnDate"))
        );
        query.select(root).where(condition);
        List<BorrowNoteDetail> listOfCustomerStillBorrowBook = entityManager.createQuery(query).getResultList();
        return borrowNoteDetailMapper.toDtos(listOfCustomerStillBorrowBook);
    }

    // get list of customer still borrow book to contact
    public List<CustomerDTO> getListOfCustomerOwnBook() {
        List<BorrowNoteDetail> listOfCustomerStillBorrowBook = getBorrowNoteDetailOfCustomerStillOwnBook();
        List<Customer> customerStillNotReturnBook = listOfCustomerStillBorrowBook.stream()
                .map(BorrowNoteDetail::getBorrowNote)
                .map(BorrowNote::getCustomer)
                .distinct()
                .collect(Collectors.toList());
        return customerMapper.toDtos(customerStillNotReturnBook);
    }

    private List<BorrowNoteDetail> getBorrowNoteDetailOfCustomerStillOwnBook() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BorrowNoteDetail> query = criteriaBuilder.createQuery(BorrowNoteDetail.class);
        Root<BorrowNoteDetail> root = query.from(BorrowNoteDetail.class);
        javax.persistence.criteria.Predicate condition = criteriaBuilder.and(
                criteriaBuilder.isNull(root.get("returnDate")),
                criteriaBuilder.greaterThan(root.get("borrowNote").get("dueDate"), LocalDate.now().minusDays(LIMITATION_OVERDUE_DAYS))
        );
        query.select(root).where(condition);
        return entityManager.createQuery(query).getResultList();
    }
}
