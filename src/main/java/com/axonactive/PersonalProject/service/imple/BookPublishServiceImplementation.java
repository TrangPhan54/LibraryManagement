package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BookPublish;
import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.BookPublishRepository;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.BookPublishService;
import com.axonactive.PersonalProject.service.dto.BookPublishDTO;
import com.axonactive.PersonalProject.service.mapper.BookPublishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookPublishServiceImplementation implements BookPublishService {
    private final BookPublishRepository bookPublishRepository;
    private final BookRepository bookRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    private final BookPublishMapper bookPublishMapper;
    @Override
    public List<BookPublishDTO> getAllBookPublish() {
        List<BookPublish> bookPublishes = bookPublishRepository.findAll();
        return bookPublishMapper.toDtos(bookPublishes);

    }

    @Override
    public BookPublishDTO createBookPublish(BookPublishDTO bookPublishDTO, Long bookID, Long publishingHouseID) {
        BookPublish bookPublish = new BookPublish();
        Book book = bookRepository.findById(bookID).orElseThrow(BookStoreException::BookNotFound);
        PublishingHouse publishingHouse = publishingHouseRepository.findById(publishingHouseID).orElseThrow(BookStoreException::PublishingHouseNotFound);
        bookPublish.setBook(book);
        bookPublish.setPublishingHouse(publishingHouse);
        return bookPublishMapper.toDto(bookPublish);
    }

    @Override
    public BookPublishDTO updateBookPublish(Long bookPublishID, BookPublishDTO bookPublishDTO) {
        BookPublish bookPublish = bookPublishRepository.findById(bookPublishID).orElseThrow();
        Book book = bookRepository.findById(bookPublishDTO.getBookID()).orElseThrow();
        PublishingHouse publishingHouse = publishingHouseRepository.findById(bookPublishDTO.getPublishingHouseID()).orElseThrow(BookStoreException::PublishingHouseNotFound);
        bookPublish.setBook(book);
        bookPublish.setPublishingHouse(publishingHouse);
        return bookPublishMapper.toDto(bookPublish);
    }

    @Override
    public void deleteBookPublishById(Long bookPublishID) {
        bookPublishRepository.deleteById(bookPublishID);

    }
}
