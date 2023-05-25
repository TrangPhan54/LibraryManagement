package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.Genre;
import com.axonactive.PersonalProject.service.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAllGenre ();
    GenreDTO createGenre (GenreDTO genreDTO);
    GenreDTO updateGenre (Long genreID, GenreDTO genreDTO);
    void deleteGenreByID (Long genreID);
     List<GenreDTO> getGenreNotLike (String name);

     GenreDTO getGenreById (Long genreID);
}
