package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.entity.Genre;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.GenreRepository;
import com.axonactive.PersonalProject.service.GenreService;
import com.axonactive.PersonalProject.service.dto.GenreDTO;
import com.axonactive.PersonalProject.service.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImplement implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    @Override
    public List<GenreDTO> getAllGenre() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toDtos(genres);
    }

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setGenreName(genre.getGenreName());
        genre =  genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    public GenreDTO updateGenre (Long genreID, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(genreID).orElseThrow(BookStoreException:: GenreNotFound);
        genre.setGenreName(genre.getGenreName());
        genre = genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    public void deleteGenreByID(Long genreID) {
        genreRepository.deleteById(genreID);

    }

    @Override
    public List<GenreDTO> getGenreNotLike(String name) {
        return genreMapper.toDtos(genreRepository.getGenreNotLike(name));
    }

    @Override
    public GenreDTO getGenreById(Long genreID) {
        return genreMapper.toDto(genreRepository.findById(genreID).orElseThrow(BookStoreException::GenreNotFound));
    }
}
