package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.GenreBookService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
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
@RequiredArgsConstructor
@RequestMapping(value = "/auth/genreBooks")
public class GenreBookResource {
    @Autowired
    private final GenreBookService genreBookService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<GenreBookDTO>> getAllGenreBook() {
        return ResponseEntity.ok(genreBookService.getAllGenreBook());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{bookId}/{genreId}")
    public ResponseEntity<GenreBookDTO> createGenreBook(@PathVariable("bookId") Long bookID,
                                                        @PathVariable("genreId") Long genreID,
                                                        @RequestBody GenreBookDTO genreBookDTO) {
        GenreBookDTO book = genreBookService.createGenreBook(genreID, bookID, genreBookDTO);
        return ResponseEntity.created(URI.create("/api/genreBooks" + book.getGenreBookID())).body(book);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{genreBookId}")
    public ResponseEntity<GenreBookDTO> updateGenreBook(@PathVariable("genreBookId") Long genreBookID,
                                                        @RequestBody GenreBookDTO genreBookDTO) {
        GenreBookDTO book = genreBookService.updateGenreBook(genreBookID, genreBookDTO);
        return ResponseEntity.created(URI.create("/api/genreBooks" + book.getGenreBookID())).body(book);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{genreAndBookId}")

    public ResponseEntity<GenreBookDTO> deleteGenreBook(@PathVariable("genreAndBookId") Long genreAndBookID) {
        genreBookService.deleteGenreBookById(genreAndBookID);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{genreID}")
    public ResponseEntity<GenreBookDTO> getGenreBookById(@PathVariable("genreID") Long genreId) {
        return ResponseEntity.ok(genreBookService.getGenreBookById(genreId));
    }
    @GetMapping("/book-by-genre")
    public ResponseEntity<List<String>> getBookByGenreNameContaining(@RequestParam("name") String name) {
        return ResponseEntity.ok(genreBookService.getByGenreNameContaining(name));
    }

    @GetMapping("/book-by-genre-and-name")
    public ResponseEntity<List<BookDTO>> getByGenreNameContainingAndBookNameContaining(@RequestParam("genreName") String genreName, @RequestParam("bookName") String bookName) {
        return ResponseEntity.ok(genreBookService.getByGenreNameContainingAndBookNameContaining(genreName, bookName));
    }
}