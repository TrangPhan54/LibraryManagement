package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Genre;
import com.axonactive.PersonalProject.entity.GenreBook;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.repository.GenreBookRepository;
import com.axonactive.PersonalProject.repository.GenreRepository;
import com.axonactive.PersonalProject.service.GenreBookService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import com.axonactive.PersonalProject.service.dto.GenreDTO;
import com.axonactive.PersonalProject.service.mapper.CustomerMapper;
import com.axonactive.PersonalProject.service.mapper.GenreBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<GenreBookDTO> getAllGenreBook() {
        List<GenreBook> genreBooks = genreBookRepository.findAll();
        return genreBookMapper.toDtos(genreBooks);
    }

    @Override
    public GenreBookDTO createGenreBook(Long genreID, Long bookID, GenreBookDTO genreBookDTO) {
        GenreBook genreBook = new GenreBook();
        Genre genre = genreRepository.findById(genreID).orElseThrow(BookStoreException::GenreNotFound);
        Book book = bookRepository.findById(bookID).orElseThrow(BookStoreException::BookNotFound);
        genreBook.setBook(book);
        genreBook.setGenre(genre);
        genreBook = genreBookRepository.save(genreBook);
        return genreBookMapper.toDto(genreBook);

    }

    @Override
    public GenreBookDTO updateGenreBook(Long genreBookID, GenreBookDTO genreBookDTO) {
        GenreBook genreBook = genreBookRepository.findById(genreBookID).orElseThrow(BookStoreException::GenreBookNotFound);
        Genre genre = genreRepository.findById(genreBookDTO.getGenreID()).orElseThrow(BookStoreException::GenreNotFound);
        Book book = bookRepository.findById(genreBookDTO.getBookID()).orElseThrow(BookStoreException::BookNotFound);
        genreBook.setBook(book);
        genreBook.setGenre(genre);
        genreBook = genreBookRepository.save(genreBook);
        return genreBookMapper.toDto(genreBook);

    }

    @Override
    public void deleteGenreBookById(Long genreBookId) {
        GenreBook genreBook = genreBookRepository.findById(genreBookId).orElseThrow(BookStoreException::GenreBookNotFound);
        genreBookRepository.delete(genreBook);

    }

    private void genreException(GenreDTO genreDTO) {
        if (genreDTO.getGenreName().isBlank() || !isAlpha(genreDTO.getGenreName())) {
            throw BookStoreException.badRequest("WrongNameFormat", "Genre Name Must Contains Only Letters And Cannot Be Empty");
        }
    }

    private void bookException(BookDTO bookDTO) {
        if (bookDTO.getBookName().isBlank() || !isAlphaOrNumeric(bookDTO.getBookName())) {
            throw BookStoreException.badRequest("WrongNameFormat", "Book Name Must Contains Letters Or Number And Cannot Be Empty");
        }
        if (bookDTO.getBookImage().isBlank()) {
            throw BookStoreException.badRequest("WrongImageFormat", "Book Must Have Images To Describe");
        }
        if (bookDTO.getPricePerBook() < 0){
            throw BookStoreException.badRequest("WrongPriceValue","The Price Of Book Must Be More Than 0");
        }
        if (bookDTO.getContentSummary().isBlank()){
            throw BookStoreException.badRequest("WrongContentFormat","Content Summary Cannot Be Empty");
        }
        if (bookDTO.getDatePublish().isAfter(LocalDate.now())){
            throw BookStoreException.badRequest("WrongDateValue"," Date Publish Must Be Before Now");
        }

    }
}
