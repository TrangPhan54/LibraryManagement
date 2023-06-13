package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BorrowNoteDetailServiceImplementationTest {
    @Autowired
    private BorrowNoteDetailService borrowNoteDetailService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void getNumberOfBookByCustomerId() {
        Long numberOfBorrowedBooks = borrowNoteDetailService.getNumberOfBookByCustomerId(1L);
        System.out.println(numberOfBorrowedBooks);

        assertEquals(numberOfBorrowedBooks, 2);
    }

    @Test
    void customerReturnBook() {
        System.out.println(borrowNoteDetailService.customerReturnBook(1L, 2L));
    }


    @Test
    void nameOfBookRemaining() {
        List<Long> ids = List.of(1L);
        List<String> tempList = borrowNoteDetailService.nameOfBookRemaining(1L, ids);
        tempList.forEach(System.out::println);
    }

    @Test
    void returnBookByCustomer() {
        List<Long> ids = List.of(17L);
        borrowNoteDetailService.returnBookByCustomer(12L, ids);
        Customer customer = customerRepository.findById(12L).get();
        System.out.println(customer.isActive());
    }

    @Test
    void countNumberMaxBorrowBook() {
        Long countBook = borrowNoteDetailService.countNumberMaxBorrowBook(1L);
        System.out.println(borrowNoteDetailService.countNumberMaxBorrowBook(1L));
        assertEquals(countBook, 2L);
    }

    @Test
    void getBookNameByBookId() {
        String books = borrowNoteDetailService.getBookNameByBookId(2L);
        System.out.println(books);

    }

    @Test
    void maxBorrowBook() {
        List<Long> id = borrowNoteDetailService.maxBorrowBook();
        id.forEach(System.out::println);
    }

    @Test
    void minBorrowBook() {
        List<Long> id = borrowNoteDetailService.minBorrowBook();
        id.forEach(System.out::println);
    }

    @Test
    void nameOfMaxBorrowBook() {
        List<Book> tempList = borrowNoteDetailService.nameOfMaxBorrowBook();
        tempList.forEach(System.out::println);
    }

    @Test
    void nameOfMinBorrowBook() {
        List<Book> tempList = borrowNoteDetailService.nameOfMinBorrowBook();
        tempList.forEach(System.out::println);
    }


    @Test
    void feeFineForReturningBookLate() {
        List<Long> ids = List.of(2L,3L);
        borrowNoteDetailService.feeFineForReturningBookLate(1L,ids);
    }

    @Test
    void getMaxBorrowBook() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date1String = "2022/01/01";
        String date2String = "2024/01/01";
        LocalDate date1 = LocalDate.parse(date1String, dateTimeFormatter);
        LocalDate date2 = LocalDate.parse(date2String, dateTimeFormatter);
        Map<Book,Long> re = borrowNoteDetailService.getMaxBorrowBook(date1,date2);
        for (Map.Entry <Book,Long> entry:re.entrySet()){
            Book bk = entry.getKey();
            Long pk = entry.getValue();
            System.out.println(bk.getName());
            System.out.println(pk);
        }

    }

//    @Test
//    void getMaxBorrowCustomer() {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        String date1String = "2022/01/01";
//        String date2String = "2024/01/01";
//        LocalDate date1 = LocalDate.parse(date1String, dateTimeFormatter);
//        LocalDate date2 = LocalDate.parse(date2String, dateTimeFormatter);
//        List<CustomerDTO> re = borrowNoteDetailService.getMaxBorrowCustomer(date1,date2);
//        System.out.println(re);
//    }

    @Test
    void getMaxCustomer() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date1String = "2022/01/01";
        String date2String = "2022/08/17";
        LocalDate date1 = LocalDate.parse(date1String, dateTimeFormatter);
        LocalDate date2 = LocalDate.parse(date2String, dateTimeFormatter);
        Map<Customer,Long> re = borrowNoteDetailService.getMaxCustomer(date1,date2);
        for (Map.Entry <Customer,Long> entry:re.entrySet()){
            Customer cus = entry.getKey();
            Long pk = entry.getValue();
            System.out.println(cus.getFirstName());
            System.out.println(cus.getLastName());
            System.out.println(pk);
        }
    }
}