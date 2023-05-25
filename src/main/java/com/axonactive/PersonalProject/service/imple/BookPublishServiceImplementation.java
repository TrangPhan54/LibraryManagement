package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BookPublish;
import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.BookPublishRepository;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.BookPublishService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.BookPublishDTO;
import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;
import com.axonactive.PersonalProject.service.mapper.BookPublishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;

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
        BookPublish bookPublish = bookPublishRepository.findById(bookPublishID).orElseThrow(BookStoreException::BookPublishNotFound);
        bookPublishRepository.delete(bookPublish);

    }
    private void bookException(BookDTO bookDTO) {
        if (bookDTO.getBookName().isBlank() || !isAlpha(bookDTO.getBookName()))
            throw BookStoreException.badRequest("WrongNameOfBookFormat", "Name Of Book Should only contains letters");
        if (bookDTO.getPricePerBook() < 0 || bookDTO.getPricePerBook().isNaN())
            throw BookStoreException.badRequest("WrongValue","Price Must Be A Number And More Than 0");

        if (bookDTO.getBookImage().isBlank()){
            throw BookStoreException.badRequest("WrongImage","Book Must Have An Image To Describe");
        }
        if (bookDTO.getContentSummary().isBlank()){
            throw BookStoreException.badRequest("EmptySummary","Summary Must Have At Least 255 Characters");
        }
        if (bookDTO.getPricePerBook()<0){
            throw BookStoreException.badRequest("WrongValue","Price Per Book Must Be More Than 0");
        }
        if (bookDTO.getDatePublish().isAfter(LocalDate.now()))
            throw BookStoreException.badRequest("WrongDate","Date Publish Must Be Before Now");

    }
    private void publishingHouseException (PublishingHouseDTO publishingHouseDTO){
        if (publishingHouseDTO.getPublishingHouseName().isBlank() || !isAlpha(publishingHouseDTO.getPublishingHouseName())){
            throw BookStoreException.badRequest("WrongNameFormat","Publishing House Name Should Contains Only Letters");
        }
    }
}
