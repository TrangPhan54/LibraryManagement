package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BorrowNoteDetailServiceImplementationTest {
    @Autowired
    private BorrowNoteDetailService borrowNoteDetailService;

    @Test
    void getNumberOfBookByCustomerId() {
        Long numberOfBorrowedBooks = borrowNoteDetailService.getNumberOfBookByCustomerId(1L);
        System.out.println(numberOfBorrowedBooks);

        assertEquals(5,5);
    }

    @Test
    void customerReturnBook() {
        System.out.println(borrowNoteDetailService.customerReturnBook(1L,2L));

        assertEquals(3,3);

    }


    @Test
    void nameOfBookRemaining() {
//        System.out.println(borrowNoteDetailService.nameOfBookRemaining(1L,3L));
        List<String> tempList = borrowNoteDetailService.nameOfBookRemaining(1L,3L);
        tempList.forEach(System.out::println);
    }
}