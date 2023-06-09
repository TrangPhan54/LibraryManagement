package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.GenreBook;
import com.axonactive.PersonalProject.service.GenreBookService;
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
class GenreBookServiceImplementationTest {
    @Autowired
    private GenreBookService genreBookService;

    @Test
    void getByGenreNameContainingAndBookNameContaining() {
        List<BookDTO> tempList = genreBookService.getByGenreNameContainingAndBookNameContaining("Classic", "The");
        tempList.forEach(System.out::println);
    }

    @Test
    void getByGenreNameContaining() {
        List<String> re = genreBookService.getByGenreNameContaining("Classic");
        re.forEach(System.out::println);
    }

    @Test
    void getBookHaveRelation() {
        List<BookDTO> re = genreBookService.getBookHaveRelation("Gone with the wind");
        System.out.println(re);

    }
}