package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Author;
import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.AuthorRepository;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;


@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImplement implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDtos(books);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO, Long publishingHouseID, Long authorID) {
        Book book = new Book();
        book.setBookName(bookDTO.getBookName());
        book.setContentSummary(bookDTO.getContentSummary());
        book.setBookImage(bookDTO.getBookImage());
        book.setPricePerBook(bookDTO.getPricePerBook());
        book.setDatePublish(bookDTO.getDatePublish());
        Author author = authorRepository.findById(authorID).orElseThrow();
        PublishingHouse publishingHouse = publishingHouseRepository.findById(publishingHouseID).orElseThrow();
        book.setAuthor(author);
        book.setPublishingHouse(publishingHouse);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }


    @Override
    public BookDTO updateBook(Long bookID, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookID).orElseThrow();
        book.setBookName(bookDTO.getBookName());
        book.setContentSummary(bookDTO.getContentSummary());
        book.setBookImage(bookDTO.getBookImage());
        book.setPricePerBook(bookDTO.getPricePerBook());
        book.setDatePublish(bookDTO.getDatePublish());
        book.setPublishingHouse(publishingHouseRepository.findById(bookDTO.getPublishingHouseID()).orElseThrow(BookStoreException::PublishingHouseNotFound));
        book.setAuthor(authorRepository.findById(bookDTO.getBookID()).orElseThrow());
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteBookById(Long bookID) {
        bookRepository.deleteById(bookID);

    }

    @Override
    public BookDTO getBookById(Long bookID) {
        return bookMapper.toDto(bookRepository.findById(bookID).orElseThrow(BookStoreException::BookNotFound));
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
}
