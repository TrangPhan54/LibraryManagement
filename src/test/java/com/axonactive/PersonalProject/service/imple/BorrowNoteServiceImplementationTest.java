package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BorrowNoteServiceImplementationTest {
    @Autowired
    private BorrowNoteService borrowNoteService;

    @Test
    void maxBorrowCustomer() {
        List<Long> ids = borrowNoteService.maxBorrowCustomer();
        ids.forEach(System.out::println);
    }

    @Test
    void nameOfMaxBorrowCustomer() {
        List<CustomerDTO> customers = borrowNoteService.nameOfMaxBorrowCustomer();
        customers.forEach(System.out::println);
    }

    @Test
    void minBorrowCustomer() {
        List<Long> ids = borrowNoteService.minBorrowCustomer();
        ids.forEach(System.out::println);
    }

    @Test
    void nameOfMinBorrowCustomer() {
        List<CustomerDTO> customerDTOS = borrowNoteService.nameOfMinBorrowCustomer();
        customerDTOS.forEach(System.out::println);
    }

    @Test
    void nameOfCustomerReturnBookLate() {
        List<String> tempList = borrowNoteService.nameOfCustomerReturnBookLate();
        tempList.forEach(System.out::println);
    }
}