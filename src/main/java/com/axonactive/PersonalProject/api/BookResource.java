package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/books")

public class BookResource {
    @Autowired
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping(value = "/{authorId}/{publishingHouseId}")
    public ResponseEntity<BookDTO> createBook(@PathVariable("authorId") Long authorID,
                                              @PathVariable("publishingHouseId") Long publishingHouseID,
                                              @RequestBody BookDTO bookDTO) {
        BookDTO book = bookService.createBook(bookDTO, publishingHouseID, authorID);
        return ResponseEntity.created(URI.create("/api/books" + book.getBookID())).body(book);
    }

    @PutMapping(value = "/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("bookId") Long bookID,
                                              @RequestBody BookDTO bookDTO) {
        BookDTO book = bookService.updateBook(bookID, bookDTO);
        return ResponseEntity.created(URI.create("/api/books" + book.getBookID())).body(book);
    }
    @DeleteMapping(value = "/{bookId}")

    public ResponseEntity<BookDTO> deleteBook (@PathVariable("bookId") Long bookID){
        bookService.deleteBookById(bookID);
        return ResponseEntity.noContent().build();
    }


}
