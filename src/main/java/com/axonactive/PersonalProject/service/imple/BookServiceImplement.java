package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.*;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.AuthorRepository;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.BookAnalyticDTO;
import com.axonactive.PersonalProject.service.dto.BookContentDTO;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.CreateBookDTO;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    // Create book title and physical books with number if copies
    @Override
    public BookDTO createBook(CreateBookDTO createBookDTO) {
        PublishingHouse publishingHouse = publishingHouseRepository.findById(createBookDTO.getPublishingHouseId()).orElseThrow(LibraryException::PublishingHouseNotFound);
        Author author = authorRepository.findById(createBookDTO.getAuthorID()).orElseThrow();
//        if (createBookDTO.getName().isBlank() || !isAlpha(createBookDTO.getName()))
//            throw LibraryException.badRequest("WrongNameOfBookFormat", "Name Of Book Should only contains letters");

        if (createBookDTO.getName().isBlank() || createBookDTO.getName().trim().isEmpty() || createBookDTO.getName() == null)
            throw LibraryException.badRequest("WrongNameOfBookFormat", "Name Of Book Should only contains letters");

        if (createBookDTO.getBookImage().isBlank()) {
            throw LibraryException.badRequest("WrongImage", "Book Must Have An Image To Describe");
        }
        if (createBookDTO.getContentSummary().isBlank()) {
            throw LibraryException.badRequest("EmptySummary", "Summary Must Have At Least 255 Characters");
        }
        if (createBookDTO.getDatePublish().isAfter(LocalDate.now()))
            throw LibraryException.badRequest("WrongDate", "Date Publish Must Be Before Now");
        Book book = Book.builder()
                .bookImage(createBookDTO.getBookImage())
                .name(createBookDTO.getName())
                .contentSummary(createBookDTO.getContentSummary())
                .datePublish(createBookDTO.getDatePublish())
                .author(author)
                .build();
        List<PhysicalBook> physicalBooksList = new ArrayList<>();
        for (long i = 0; i < createBookDTO.getNumberOfPhysicalBook(); i++) {
            PhysicalBook physicalBook = PhysicalBook.builder()
                    .book(book)
                    .importDate(createBookDTO.getImportDate())
                    .importPrice(createBookDTO.getImportPrice())
                    .publishingHouse(publishingHouse)
                    .build();
            physicalBooksList.add(physicalBook);
        }
        book.setPhysicalBookList(physicalBooksList);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDTO updateBook(Long bookID, BookDTO bookDTO) {
        if (bookDTO.getName().isBlank() || !isAlpha(bookDTO.getName()))
            throw LibraryException.badRequest("WrongNameOfBookFormat", "Name Of Book Should only contains letters");


        if (bookDTO.getBookImage().isBlank()) {
            throw LibraryException.badRequest("WrongImage", "Book Must Have An Image To Describe");
        }
        if (bookDTO.getContentSummary().isBlank()) {
            throw LibraryException.badRequest("EmptySummary", "Summary Must Have At Least 255 Characters");
        }


        if (bookDTO.getDatePublish().isAfter(LocalDate.now()))
            throw LibraryException.badRequest("WrongDate", "Date Publish Must Be Before Now");
        Book book = bookRepository.findById(bookID).orElseThrow(LibraryException::BookNotFound);
        book.setName(bookDTO.getName());
        book.setContentSummary(bookDTO.getContentSummary());
        book.setBookImage(bookDTO.getBookImage());
        book.setDatePublish(bookDTO.getDatePublish());
//        book.setPublishingHouse(publishingHouseRepository.findById(bookDTO.getPublishingHouseID()).orElseThrow(LibraryException::PublishingHouseNotFound));
        book.setAuthor(authorRepository.findById(bookDTO.getAuthorID()).orElseThrow(LibraryException::AuthorNotFound));
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteBookById(Long bookID) {
        Book book = bookRepository.findById(bookID).orElseThrow(LibraryException::BookNotFound);
        bookRepository.delete(book);

    }

    @Override
    public BookDTO getBookById(Long bookID) {
        return bookMapper.toDto(bookRepository.findById(bookID).orElseThrow(LibraryException::BookNotFound));
    }

    // 1. Tim sach co chua ki tu khong phan biet hoa thuong
    @Override
    public List<BookDTO> getByBookNameContainingIgnoreCase(String keyword) {
        return bookMapper.toDtos(bookRepository.findByNameContainingIgnoreCase(keyword));
    }
    @Override
    public List<BookDTO> getByName(String name) {
        return bookMapper.toDtos(bookRepository.findByName(name));
    }
    // 2. Tim sach boi trang thai (available or unavailable)

//    public List<BookDTO> getByStatus(Status status) {
//        return bookMapper.toDtos(bookRepository.findByStatus(status));
//    }


    // 3. Tim sach boi ten tac gia
    @Override
    public List<BookDTO> getBookByAuthorFirstName(String authorFirstName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorFirstName(authorFirstName));
    }

    //4 .Tim sach boi ho tac gia
    @Override
    public List<BookDTO> getBookByAuthorLastName(String authorLastName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorLastName(authorLastName));
    }

    //5. Tim noi dung sach thong qua ten sach
    @Override
    public BookContentDTO findContentSummaryByBookName(String bookName) {
        return bookRepository.findContentSummaryByBookName(bookName);
    }

    //6. Tim noi dung sach thong qua ten sach co chua ki tu nao do
    @Override
    public BookContentDTO findContentSummaryByBookNameContaining(String bookName) {
        return bookRepository.findContentSummaryByBookNameContaining("%" + bookName + "%");
    }
    //7. Tim ten sach dua vao ho tac gia co chua ki tu nao do

    @Override
    public List<BookDTO> getBookByAuthorLastNameContaining(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorLastNameContaining("%" + partOfName + "%"));
    }
    //8. Tim ten sach dua vao ten tac gia co chua ki tu nao do

    @Override
    public List<BookDTO> getBookByAuthorFirstNameContaining(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorFirstNameContaining("%" + partOfName + "%"));
    }

    //9. Tim ten sach dua vao ho tac gia co chua ki tu nao do khong phan biet hoa thuong
    @Override
    public List<BookDTO> getBookByAuthorLastNameContainingIgnoreCase(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorLastNameContainingIgnoreCase(partOfName));
    }
    //10. Tim ten sach dua vao ten tac gia co chua ki tu nao do khong phan biet hoa thuong
    @Override
    public List<BookDTO> getBookByAuthorFirstNameContainingIgnoreCase(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorFirstNameContainingIgnoreCase(partOfName));
    }

    @Override
    public List<BookDTO> findAllById(Iterable<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        for (Book book: books){
            System.out.println(book.getName());
        }
        return bookMapper.toDtos(books);
    }
    //11. Book statistics base on book name
    @Override
    public List<BookAnalyticDTO> getBookAnalytic() {
        return bookRepository.getBookAnalytic();
    }

}
