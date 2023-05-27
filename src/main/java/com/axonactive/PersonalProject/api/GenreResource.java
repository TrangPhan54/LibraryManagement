package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Genre;
import com.axonactive.PersonalProject.service.AuthorService;
import com.axonactive.PersonalProject.service.GenreService;
import com.axonactive.PersonalProject.service.dto.AuthorDTO;
import com.axonactive.PersonalProject.service.dto.GenreDTO;
import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/auth/genres")
@RequiredArgsConstructor
public class GenreResource {
    @Autowired
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenre() {
        return ResponseEntity.ok(genreService.getAllGenre());
    }


    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO genre = genreService.createGenre(genreDTO);
        return ResponseEntity.created(URI.create("/api/genres/" + genre.getGenreID())).body(genre);


    }

    @PutMapping(value = "/{genreID}")
    public ResponseEntity<GenreDTO> updateGenre (@PathVariable("genreDTO") Long genreID, @RequestBody GenreDTO genreDTO) {
        GenreDTO genre = genreService.updateGenre(genreID,genreDTO);
        return ResponseEntity.created(URI.create("/api/genres/" + genre.getGenreID())).body(genre);


    }

    @DeleteMapping(value = "/{genreId}")
    public ResponseEntity<GenreDTO> deleteGenre(@PathVariable("genreId") Long genreID) {
        genreService.deleteGenreByID(genreID);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/get_genre_not_like")
    public ResponseEntity<List<GenreDTO>> getGenreNotLike(@RequestParam ("name") String name){
        return ResponseEntity.ok().body(genreService.getGenreNotLike(name));
    }




}