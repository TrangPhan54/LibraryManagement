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
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import com.axonactive.PersonalProject.service.mapper.CustomerMapper;
import com.axonactive.PersonalProject.service.mapper.GenreBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
        Book book = bookRepository.findById(bookID).orElseThrow(BookStoreException:: BookNotFound);
        genreBook.setBook(book);
        genreBook.setGenre(genre);
        genreBook = genreBookRepository.save(genreBook);
        return genreBookMapper.toDto(genreBook);

    }

    @Override
    public GenreBookDTO updateGenreBook(Long genreBookID, GenreBookDTO genreBookDTO) {
        GenreBook genreBook = genreBookRepository.findById(genreBookID).orElseThrow(BookStoreException::GenreBookNotFound);
        Genre genre = genreRepository.findById(genreBookDTO.getGenreID()).orElseThrow(BookStoreException:: GenreNotFound);
        Book book = bookRepository.findById(genreBookDTO.getBookID()).orElseThrow(BookStoreException:: BookNotFound);
        genreBook.setBook(book);
        genreBook.setGenre(genre);
        genreBook = genreBookRepository.save(genreBook);
        return genreBookMapper.toDto(genreBook);



    }

    @Override
    public void deleteGenreBookById(Long genreBookId) {
        genreRepository.deleteById(genreBookId);

    }
}
