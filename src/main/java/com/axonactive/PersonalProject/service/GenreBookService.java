package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.GenreBookDTO;

import java.util.List;

public interface GenreBookService {
    List<GenreBookDTO> getAllGenreBook();
    GenreBookDTO createGenreBook (Long genreID,Long bookID, GenreBookDTO genreBookDTO);
    GenreBookDTO updateGenreBook (Long genreBookID, GenreBookDTO genreBookDTO);
    void deleteGenreBookById (Long genreBookId);
}
