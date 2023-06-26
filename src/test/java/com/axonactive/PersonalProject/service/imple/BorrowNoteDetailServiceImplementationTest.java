package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.repository.BorrowNoteDetailRepository;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.customedDto.BookAnalyticForAmountOfTimeDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.CustomerWithNumberOfPhysicalCopiesBorrowDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.ReturnBookByCustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BorrowNoteDetailServiceImplementationTest {
    @Autowired
    private BorrowNoteDetailService borrowNoteDetailService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BorrowNoteDetailRepository borrowNoteDetailRepository;

    @Test
    void getNumberOfBookByCustomerId() {
        Long numberOfBorrowedBooks = borrowNoteDetailService.getNumberOfBookByCustomerId(21L);
        System.out.println(numberOfBorrowedBooks);

//        assertEquals(numberOfBorrowedBooks, 2);
    }

    @Test
    void customerReturnBook() {
        System.out.println(borrowNoteDetailService.customerReturnBook(1L, 2L));
    }


    @Test
    void nameOfBookRemaining() {
        List<Long> ids = List.of(1L,2L);
        List<String> tempList = borrowNoteDetailService.nameOfBookRemaining(1L, ids);
        tempList.forEach(System.out::println);
    }

    @Test
    void returnBookByCustomer() {
        ReturnBookByCustomerDTO returnBookByCustomerDto = new ReturnBookByCustomerDTO();
        returnBookByCustomerDto.setCustomerId(1L);
        List<Long> physicalBookIds = new ArrayList<>();
        physicalBookIds.add(40L);
        physicalBookIds.add(12L);

        returnBookByCustomerDto.setPhysicalBookIds(physicalBookIds);
        System.out.println(returnBookByCustomerDto.getPhysicalBookIds());
        borrowNoteDetailService.banAccountForReturningBookLate(returnBookByCustomerDto);
//
        Customer customer = customerRepository.findById(1L).get();
//        System.out.println(customer.get);
    }



    @Test
    void fineFeeForReturningBookLate() {
        ReturnBookByCustomerDTO returnBookByCustomerDto = new ReturnBookByCustomerDTO();
        returnBookByCustomerDto.setCustomerId(21L);
        List<Long> physicalBookIds = new ArrayList<>();
        physicalBookIds.add(43L);
        physicalBookIds.add(13L);
        returnBookByCustomerDto.setPhysicalBookIds(physicalBookIds);
        borrowNoteDetailService.fineFeeForReturningBookLate(returnBookByCustomerDto);
        System.out.println(returnBookByCustomerDto.getPhysicalBookIds());
    }

    @Test
    void getMaxBorrowBook() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date1String = "2022/01/01";
        String date2String = "2024/01/01";
        LocalDate date1 = LocalDate.parse(date1String, dateTimeFormatter);
        LocalDate date2 = LocalDate.parse(date2String, dateTimeFormatter);
        List<BookAnalyticForAmountOfTimeDTO> re = borrowNoteDetailService.getMaxBorrowBook(date1,date2);
        re.forEach(System.out::println);

    }

    @Test
    void getMaxCustomer() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date1String = "2022/01/01";
        String date2String = "2024/01/01";
        LocalDate date1 = LocalDate.parse(date1String, dateTimeFormatter);
        LocalDate date2 = LocalDate.parse(date2String, dateTimeFormatter);
        List<CustomerWithNumberOfPhysicalCopiesBorrowDTO> re = borrowNoteDetailService.getMaxCustomer(date1,date2);
        re.forEach(System.out::println);
    }

    @Test
    void getBookListOfACustomer() {
        Long id = 15L;
        borrowNoteDetailService.getBookListOfACustomer(id);
    }

    @Test
    void getBookListOfACustomer1() {
        Long id = 6L;
        List<BorrowNoteDetail> brdList = borrowNoteDetailService.getBookListOfACustomer1(id);
        brdList.forEach(System.out::println);
    }


//    @Test
//    void createBorrowNoteDetail() {
//        Long phyID = 40L;
//        Long borrowNoteID = 30L;
//        BorrowNoteDetailDTO re = borrowNoteDetailService.createBorrowNoteDetail(phyID,borrowNoteID);
//        System.out.println(re);
//    }
}