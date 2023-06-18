package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.CreatePhysicalBookDto;
import com.axonactive.PersonalProject.service.dto.PhysicalBookDTO;

import java.util.List;

public interface PhysicalBookService {
    List<PhysicalBookDTO> getAllPhysicalBook();

    PhysicalBookDTO createPhysicalBook(CreatePhysicalBookDto createPhysicalBookDto);

    PhysicalBookDTO updatePhysicalBook(PhysicalBookDTO bookDTO);

    void deletePhysicalBookById(Long physicalBookID);
    // Tim sach vat li dua vao ten nha xuat ban
    List<PhysicalBookDTO> findPhysicalBookByPublishingHouseName (String publishingHouseName);
    // Dem co bao nhieu cuon sach vat li co cung mot tua de
    Long countBookBaseOnBookName (String bookName);
}
