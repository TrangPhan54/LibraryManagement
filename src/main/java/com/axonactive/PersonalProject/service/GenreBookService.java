package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.GenreBook;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;

import java.util.List;

public interface GenreBookService {
    List<GenreBookDTO> getAllGenreBook();

    GenreBookDTO createGenreBook(Long genreID, Long bookID, GenreBookDTO genreBookDTO);

    GenreBookDTO updateGenreBook(Long genreBookID, GenreBookDTO genreBookDTO);

    void deleteGenreBookById(Long genreBookId);

    GenreBookDTO getGenreBookById(Long genreBookId);

    List<String> getByGenreNameContaining(String name);

    List<BookDTO> getByGenreNameContainingAndBookNameContaining(String genreName, String bookName);

    List<BookDTO> getBookHaveRelation (String bookName);
}
