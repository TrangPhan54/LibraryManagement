package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.*;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.PhysicalBookRepository;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.PhysicalBookService;
import com.axonactive.PersonalProject.service.dto.CreatePhysicalBookDto;
import com.axonactive.PersonalProject.service.dto.ListOfPhysicalBookDTO;
import com.axonactive.PersonalProject.service.dto.PhysicalBookDTO;

import com.axonactive.PersonalProject.service.mapper.PhysicalBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhysicalBookServiceImplement implements PhysicalBookService {
    private final PublishingHouseRepository publishingHouseRepository;
    private final PhysicalBookRepository physicalBookRepository;
    private final PhysicalBookMapper physicalBookMapper;
    private final BookRepository bookRepository;
    private static final Long LIMITATION_NUMBER_OF_DAYS = 2000L;
    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public List<PhysicalBookDTO> getAllPhysicalBook() {
        List<PhysicalBook> physicalBooks = physicalBookRepository.findAll();
        return physicalBookMapper.toDtos(physicalBooks);
    }

    @Override
    public PhysicalBookDTO createPhysicalBook(CreatePhysicalBookDto createPhysicalBookDto) {
        PhysicalBook physicalBook = new PhysicalBook();
        Book book = bookRepository.findById(createPhysicalBookDto.getBookId()).orElseThrow(LibraryException::BookNotFound);
        PublishingHouse publishingHouse = publishingHouseRepository.findById(createPhysicalBookDto.getPublishingHouseId()).orElseThrow(LibraryException::PublishingHouseNotFound);

        physicalBook.setBook(book);
        physicalBook.setPublishingHouse(publishingHouse);
        physicalBook.setImportDate(createPhysicalBookDto.getImportDate());
        physicalBook.setImportPrice(createPhysicalBookDto.getImportPrice());
        physicalBook = physicalBookRepository.save(physicalBook);
        return physicalBookMapper.toDto(physicalBook);

    }

    @Override
    public PhysicalBookDTO updatePhysicalBook(PhysicalBookDTO physicalBookDTO) {
        PhysicalBook physicalBook = physicalBookRepository.findById(physicalBookDTO.getId()).orElseThrow();
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
        List<PhysicalBook> physicalBook = physicalBookRepository.findAll().stream().filter(pb -> pb.getPublishingHouse().getName().equalsIgnoreCase(publishingHouseName)).collect(Collectors.toList());
        return physicalBookMapper.toDtos(physicalBook);
    }

    @Override
    public Long countBookBaseOnBookName(String bookName) {
        return physicalBookRepository.countBookBaseOnBookName(bookName);
    }

    //Function: Set old book into liquidation
    public List<PhysicalBookDTO> getLiquidationBook() {
        List<PhysicalBook> physicalBookList = physicalBookRepository.findAll();
        List<PhysicalBook> liquidationBookList = new ArrayList<>();
        Predicate<Long> lowerThanLimitationOfDay = x -> x < LIMITATION_NUMBER_OF_DAYS;
        for (PhysicalBook physicalBook : physicalBookList) {
            long differenceOfTime = ChronoUnit.DAYS.between(physicalBook.getImportDate(), LocalDate.now());
            if (lowerThanLimitationOfDay.test(differenceOfTime)) {
                physicalBook.setStatus(Status.GOOD);
                liquidationBookList.add(physicalBook);
            } else {
                physicalBook.setStatus(Status.LIQUIDATION);
                liquidationBookList.add(physicalBook);
            }
        }
        return physicalBookMapper.toDtos(liquidationBookList);
    }


    // Function: get physical book by its status

    @Override
    public List<PhysicalBookDTO> getByStatus(Status status) {
        return physicalBookMapper.toDtos(physicalBookRepository.findByStatus(status));
    }
    // Function: get some physical book by ids

    @Override
    public List<PhysicalBookDTO> findAllById(ListOfPhysicalBookDTO listOfPhysicalBookDTO) {
        List<PhysicalBook> physicalBooks = physicalBookRepository.findAllById(listOfPhysicalBookDTO.getPhysicalBookIds());
        return physicalBookMapper.toDtos(physicalBooks);
    }


}
