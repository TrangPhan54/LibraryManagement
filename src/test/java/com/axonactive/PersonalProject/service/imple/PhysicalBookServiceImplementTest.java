package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.PhysicalBook;
import com.axonactive.PersonalProject.service.PhysicalBookService;
import com.axonactive.PersonalProject.service.dto.PhysicalBookDTO;
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
class PhysicalBookServiceImplementTest {
    @Autowired
    private PhysicalBookService physicalBookService;

    @Test
    void findPhysicalBookByPublishingHouseName() {
        List<PhysicalBookDTO> physicalBookDTOList = physicalBookService.findPhysicalBookByPublishingHouseName("Nha xuat ban Giao Duc Viet Nam");
        physicalBookDTOList.forEach(System.out::println);
    }

    @Test
    void countBookBaseOnBookName() {
        Long num = physicalBookService.countBookBaseOnBookName("Bai tap nang cao va mot so chuyen de toan 8");
        System.out.println(num);
    }

    @Test
    void getLiquidationBook() {
        List<PhysicalBookDTO> list = physicalBookService.getLiquidationBook();
        System.out.println(list);
    }
}