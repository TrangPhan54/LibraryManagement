package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.BookAnalyticDTO;
import com.axonactive.PersonalProject.service.dto.BookDTO;
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
class BookServiceImplementTest {
    @Autowired
    private BookService bookService;

    @Test
    void findAllById() {
        Iterable<Long> ids = List.of(21L,22L);
        List<BookDTO> re = bookService.findAllById(ids);
        System.out.println(re);

    }

    @Test
    void numberOfBookBaseOnTitle() {
        List <BookAnalyticDTO> numOfBooks = bookService.getBookAnalytic();
        numOfBooks.forEach(System.out::println);
    }
}