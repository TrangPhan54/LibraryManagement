package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
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
class BorrowNoteServiceImplementationTest {
    @Autowired
    private BorrowNoteService borrowNoteService;

    @Test
    void nameOfCustomerReturnBookLate() {
        List<String> tempList = borrowNoteService.nameOfCustomerReturnBookLate();
        tempList.forEach(System.out::println);
    }

    @Test
    void createBorrowNote() {
        Long cusID = 21L;
        Long book1 = 1L;
        Long book2 = 2L;
        Long book3 = 3L;
        Long book4 = 4L;
        Long book5 = 5L;
        Long book6 = 6L;
        Long book7 = 7L;
        Long book8 = 8L;
        Long book9 = 9L;
        List <Long> physicalBookIDList = new ArrayList<>();
        physicalBookIDList.add(book1);
        physicalBookIDList.add(book2);
        physicalBookIDList.add(book3);
        physicalBookIDList.add(book4);
        physicalBookIDList.add(book5);
        physicalBookIDList.add(book6);
        physicalBookIDList.add(book7);
        physicalBookIDList.add(book8);
        physicalBookIDList.add(book9);


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date1String = "2022/01/01";
        LocalDate date1 = LocalDate.parse(date1String, dateTimeFormatter);
        CreateBorrowNoteDTO createBorrowNoteDTO = new CreateBorrowNoteDTO();
        createBorrowNoteDTO.setBorrowDate(date1);
        createBorrowNoteDTO.setCustomerID(cusID);
        createBorrowNoteDTO.setPhysicalBookIdList(physicalBookIDList);
        borrowNoteService.createBorrowNote(createBorrowNoteDTO);
        System.out.println(createBorrowNoteDTO);

    }
}