package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Genre;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.GenreRepository;
import com.axonactive.PersonalProject.service.GenreService;
import com.axonactive.PersonalProject.service.dto.GenreDTO;
import com.axonactive.PersonalProject.service.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;

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
        if (!isAlpha(genreDTO.getName()) || genreDTO.getName().isBlank()){
            throw LibraryException.badRequest("WrongGenreFormat","Genre Should Contains Only Letters And Not Be Empty");
        }
        Genre genre = new Genre();
        genre.setName(genre.getName());
        genre =  genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    public GenreDTO updateGenre (Long genreID, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(genreID).orElseThrow(LibraryException:: GenreNotFound);
        if (!isAlpha(genreDTO.getName()) || genreDTO.getName().isBlank()){
            throw LibraryException.badRequest("WrongGenreFormat","Genre Should Contains Only Letters And Not Be Empty");
        }
        genre.setName(genre.getName());
        genre = genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    public void deleteGenreByID(Long genreID) {
        Genre genre = genreRepository.findById(genreID).orElseThrow(LibraryException:: GenreNotFound);
        genreRepository.delete(genre);

    }

    @Override
    public List<GenreDTO> getGenreNotLike(String name) {
        return genreMapper.toDtos(genreRepository.getGenreNotLike(name));
    }

    @Override
    public GenreDTO getGenreById(Long genreID) {
        return genreMapper.toDto(genreRepository.findById(genreID).orElseThrow(LibraryException::GenreNotFound));
    }
//    private void genreException (GenreDTO genreDTO){
//        if (!isAlpha(genreDTO.getName()) || genreDTO.getName().isBlank()){
//            throw LibraryException.badRequest("WrongGenreFormat","Genre Should Contains Only Letters And Not Be Empty");
//        }
//    }
}
