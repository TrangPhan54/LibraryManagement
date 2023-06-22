package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Genre;
import com.axonactive.PersonalProject.entity.GenreBook;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.GenreBookRepository;
import com.axonactive.PersonalProject.repository.GenreRepository;
import com.axonactive.PersonalProject.service.GenreBookService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import com.axonactive.PersonalProject.service.dto.GenreDTO;
import com.axonactive.PersonalProject.service.mapper.BookMapper;
import com.axonactive.PersonalProject.service.mapper.GenreBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;
import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlphaOrNumeric;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreBookServiceImplementation implements GenreBookService {

    private final GenreBookRepository genreBookRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final GenreBookMapper genreBookMapper;
    private final BookMapper bookMapper;

    @Override
    public List<GenreBookDTO> getAllGenreBook() {
        List<GenreBook> genreBooks = genreBookRepository.findAll();
        return genreBookMapper.toDtos(genreBooks);
    }

    @Override
    public GenreBookDTO createGenreBook(Long genreID, Long bookID, GenreBookDTO genreBookDTO) {
        GenreBook genreBook = new GenreBook();
        Genre genre = genreRepository.findById(genreID).orElseThrow(LibraryException::GenreNotFound);
        Book book = bookRepository.findById(bookID).orElseThrow(LibraryException::BookNotFound);
        genreBook.setBook(book);
        genreBook.setGenre(genre);
        genreBook = genreBookRepository.save(genreBook);
        return genreBookMapper.toDto(genreBook);

    }

    @Override
    public GenreBookDTO updateGenreBook(Long genreBookID, GenreBookDTO genreBookDTO) {
        GenreBook genreBook = genreBookRepository.findById(genreBookID).orElseThrow(LibraryException::GenreBookNotFound);
        Genre genre = genreRepository.findById(genreBookDTO.getGenreID()).orElseThrow(LibraryException::GenreNotFound);
        Book book = bookRepository.findById(genreBookDTO.getBookID()).orElseThrow(LibraryException::BookNotFound);
        genreBook.setBook(book);
        genreBook.setGenre(genre);
        genreBook = genreBookRepository.save(genreBook);
        return genreBookMapper.toDto(genreBook);

    }

    @Override
    public void deleteGenreBookById(Long genreBookId) {
        GenreBook genreBook = genreBookRepository.findById(genreBookId).orElseThrow(LibraryException::GenreBookNotFound);
        genreBookRepository.delete(genreBook);

    }

    @Override
    public GenreBookDTO getGenreBookById(Long genreBookId) {
        return genreBookMapper.toDto(genreBookRepository.findById(genreBookId).orElseThrow(LibraryException::GenreBookNotFound));
    }

    //find book by genre name containing
    @Override
    public List<String> getByGenreNameContaining(String name) {
        return genreBookRepository.findByGenreNameContaining(name).stream().map(gb -> gb.getBook().getName()).collect(Collectors.toList());
    }
    //find book by genre name containing and title containing
    @Override
    public List<BookDTO> getByGenreNameContainingAndBookNameContaining(String genreName, String bookName) {
        return bookMapper.toDtos(genreBookRepository.findByGenreNameContainingAndBookNameContaining(genreName, bookName).stream().map(GenreBook::getBook).collect(Collectors.toList()));
    }

    // find book that have the same genre for recommendation
    @Override
    public List<BookDTO> getBookHaveRelation(String bookName) {
        List<GenreBook> relationalBook = genreBookRepository.findAll().stream().filter(gb -> gb.getBook().getName().equalsIgnoreCase(bookName)).collect(Collectors.toList());
        Optional<String> getGenre = relationalBook.stream().map(GenreBook::getGenre).map(Genre::getName).findFirst();
        List<Book> result = genreBookRepository.findAll().stream().filter(gb -> gb.getGenre().getName().equalsIgnoreCase(getGenre.get())).map(GenreBook::getBook).collect(Collectors.toList());
        return bookMapper.toDtos(result);
    }


}
