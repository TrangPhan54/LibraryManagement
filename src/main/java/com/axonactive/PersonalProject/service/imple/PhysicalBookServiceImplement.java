package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.*;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.PhysicalBookRepository;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.PhysicalBookService;
import com.axonactive.PersonalProject.service.dto.PhysicalBookDTO;
import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;
import com.axonactive.PersonalProject.service.mapper.PhysicalBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhysicalBookServiceImplement implements PhysicalBookService {
    private final PublishingHouseRepository publishingHouseRepository;
    private final PhysicalBookRepository physicalBookRepository;
    private final PhysicalBookMapper physicalBookMapper;
    private final BookRepository bookRepository;


    @Override
    public List<PhysicalBookDTO> getAllPhysicalBook() {
        List<PhysicalBook> physicalBooks = physicalBookRepository.findAll();
        return physicalBookMapper.toDtos(physicalBooks);
    }

    @Override
    public PhysicalBookDTO createPhysicalBook(PhysicalBookDTO physicalBookDTO, Long publishingHouseID, Long bookID) {
        PhysicalBook physicalBook = new PhysicalBook();
        Book book = bookRepository.findById(bookID).orElseThrow(LibraryException::BookNotFound);
        PublishingHouse publishingHouse = publishingHouseRepository.findById(publishingHouseID).orElseThrow(LibraryException::PublishingHouseNotFound);

        physicalBook.setBook(book);
        physicalBook.setPublishingHouse(publishingHouse);
        physicalBook.setImportDate(physicalBookDTO.getImportDate());
        physicalBook.setImportPrice(physicalBookDTO.getImportPrice());
        physicalBook = physicalBookRepository.save(physicalBook);


        return physicalBookMapper.toDto(physicalBook);

    }

    @Override
    public PhysicalBookDTO updatePhysicalBook(Long physicalBookID, PhysicalBookDTO physicalBookDTO) {
        PhysicalBook physicalBook = physicalBookRepository.findById(physicalBookID).orElseThrow();
        physicalBook.setBook(bookRepository.findById(physicalBookDTO.getBookID()).orElseThrow(LibraryException::BookNotFound));
        physicalBook.setPublishingHouse(publishingHouseRepository.findById(physicalBookDTO.getPublishingHouseID()).orElseThrow(LibraryException::PublishingHouseNotFound));
        physicalBook.setImportPrice(physicalBookDTO.getImportPrice());
        physicalBook.setImportDate(physicalBookDTO.getImportDate());

//        book.setPublishingHouse(publishingHouseRepository.findById(bookDTO.getPublishingHouseID()).orElseThrow(LibraryException::PublishingHouseNotFound));

        return physicalBookMapper.toDto(physicalBook);
    }

    @Override
    public void deletePhysicalBookById(Long physicalBookID) {
        PhysicalBook physicalBook = physicalBookRepository.findById(physicalBookID).orElseThrow(LibraryException::PhysicalBookNotFound);
        physicalBookRepository.delete(physicalBook);

    }
    // Tim sach vat li dua vao ten nha xuat ban
    @Override
    public List<PhysicalBookDTO> findPhysicalBookByPublishingHouseName(String publishingHouseName) {
        List<PhysicalBook> physicalBook = physicalBookRepository.findAll().stream().filter(pb->pb.getPublishingHouse().getName().equalsIgnoreCase(publishingHouseName)).collect(Collectors.toList());
        return physicalBookMapper.toDtos(physicalBook);
    }
    // Dem co bao nhieu cuon sach vat li co cung mot tua de
    @Override
    public Long countBookBaseOnBookName(String bookName) {
        Long number = physicalBookRepository.countBookBaseOnBookName(bookName);
        return number;
    }

}
