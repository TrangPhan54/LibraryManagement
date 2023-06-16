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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/auth/genres")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
public class GenreResource {
    @Autowired
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenre() {
        return ResponseEntity.ok(genreService.getAllGenre());
    }


    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        log.info ("create genre");
        GenreDTO genre = genreService.createGenre(genreDTO);
        return ResponseEntity.created(URI.create("/api/genres/" + genre.getId())).body(genre);


    }

    @PutMapping(value = "/{genreID}")
    public ResponseEntity<GenreDTO> updateGenre (@PathVariable("genreDTO") Long genreID, @RequestBody GenreDTO genreDTO) {
        log.info ("update genre by id {}",genreID);
        GenreDTO genre = genreService.updateGenre(genreID,genreDTO);
        return ResponseEntity.created(URI.create("/api/genres/" + genre.getId())).body(genre);


    }

    @DeleteMapping(value = "/{genreId}")
    public ResponseEntity<GenreDTO> deleteGenre(@PathVariable("genreId") Long genreID) {
        log.info ("delete genre by id {}",genreID);
        genreService.deleteGenreByID(genreID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getGenreById (@PathVariable ("genreId") Long genreId){
        log.info ("get genre by id {}",genreId);
        return ResponseEntity.ok(genreService.getGenreById(genreId));
    }


    @GetMapping("/get_genre_not_like")
    public ResponseEntity<List<GenreDTO>> getGenreNotLike(@RequestParam ("name") String name){
        return ResponseEntity.ok().body(genreService.getGenreNotLike(name));
    }




}