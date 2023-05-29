package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.service.ResponseService;
import com.axonactive.PersonalProject.service.dto.ResponseDTO;
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
class ResponseServiceImplementTest {
    @Autowired
    private ResponseService responseService;

    @Test
    void findResponseOfABookByBookName() {
        String res = responseService.findResponseOfABookByBookName("Gone with the wind");
        System.out.println(res);
    }

    @Test
    void getResponseOfABook() {
        String res = responseService.getResponseOfABook(10L);
        System.out.println(res);
    }
}