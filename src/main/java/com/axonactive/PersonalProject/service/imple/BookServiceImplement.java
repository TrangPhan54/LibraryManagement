package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.*;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.AuthorRepository;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.*;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;


@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImplement implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    private final BookMapper bookMapper;
    @PersistenceContext
    private final EntityManager entityManager;

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

    // find book by name containing ignore case
    @Override
    public List<BookDTO> getByBookNameContainingIgnoreCase(String keyword) {
        return bookMapper.toDtos(bookRepository.findByNameContainingIgnoreCase(keyword));
    }

    // find book by name
    @Override
    public List<BookDTO> getByName(String name) {
        return bookMapper.toDtos(bookRepository.findByName(name));
    }


    // find book by author first name
    @Override
    public List<BookDTO> getBookByAuthorFirstName(String authorFirstName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorFirstName(authorFirstName));
    }

    // find book by author last name
    @Override
    public List<BookDTO> getBookByAuthorLastName(String authorLastName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorLastName(authorLastName));
    }

    // find content summary by book title
    @Override
    public BookContentDTO findContentSummaryByBookName(String bookName) {
        return bookRepository.findContentSummaryByBookName(bookName);
    }

    // find book name by book title containing
    @Override
    public BookContentDTO findContentSummaryByBookNameContaining(String bookName) {
        return bookRepository.findContentSummaryByBookNameContaining("%" + bookName + "%");
    }
    //find book name by author last name containing

    @Override
    public List<BookDTO> getBookByAuthorLastNameContaining(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorLastNameContaining("%" + partOfName + "%"));
    }

    //find book name by author first name containing
    @Override
    public List<BookDTO> getBookByAuthorFirstNameContaining(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorFirstNameContaining("%" + partOfName + "%"));
    }

    //find book name by author last name containing ignore case
    @Override
    public List<BookDTO> getBookByAuthorLastNameContainingIgnoreCase(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorLastNameContainingIgnoreCase(partOfName));
    }

    //find book name by author first name containing ignore case
    @Override
    public List<BookDTO> getBookByAuthorFirstNameContainingIgnoreCase(String partOfName) {
        return bookMapper.toDtos(bookRepository.findBookByAuthorFirstNameContainingIgnoreCase(partOfName));
    }

    // find all book by list ids of books
    @Override
    public List<BookDTO> findAllById(Iterable<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        for (Book book : books) {
            System.out.println(book.getName());
        }
        return bookMapper.toDtos(books);
    }

    // Book statistics base on book name
    @Override
    public List<BookAnalyticDTO> getBookAnalytic() {
        return bookRepository.getBookAnalytic();
    }

    // Use criteria builder and query to find book by name containing
    public List<BookDTO> findBookByNameContaining(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root)
                .where(cb.and(
                        cb.like(root.get("name"), "%" + name + "%")
                ));
        return bookMapper.toDtos(entityManager.createQuery(query).getResultList());
    }
    // Use criteria builder to get books that were published before 2000
    public List<BookDTO> getBookPublishBefore2000 (){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Predicate condition = criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("datePublish"), LocalDate.of(2000, 1, 1))
        );
        query.select(root).where(condition);
        List<Book> books = entityManager.createQuery(query).getResultList();
        return bookMapper.toDtos(books);
    }
    // use criteria builder to find book by author first name
    public List<BookDTO> getBookByAuthorFirstName1 (String firstName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Predicate condition = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("author").get("firstName"), firstName )
        );
        query.select(root).where(condition);
        List<Book> books = entityManager.createQuery(query).getResultList();
        return bookMapper.toDtos(books);
    }
    // use criteria builder to find book by genre name
    public List<BookDTO> getBookByGenreName (String genreName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GenreBook> query = criteriaBuilder.createQuery(GenreBook.class);
        Root<GenreBook> root = query.from(GenreBook.class);
        Predicate condition = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("genre").get("name"), genreName )
        );
        query.select(root).where(condition);
        List<Book> books = entityManager.createQuery(query).getResultList().stream().map(GenreBook::getBook).collect(Collectors.toList());
        return bookMapper.toDtos(books);
    }
}
