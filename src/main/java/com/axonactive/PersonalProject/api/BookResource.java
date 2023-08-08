package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.*;
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
@RequestMapping(value = "/auth/books")

public class BookResource {
    @Autowired
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBook() {
        log.info("get all book");
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDTO> createBook(@RequestBody CreateBookDTO createBookDTO) {
        log.info("create book");
        BookDTO book = bookService.createBook(createBookDTO);
        return ResponseEntity.created(URI.create("/api/books" + book.getId())).body(book);
    }

    @PutMapping(value = "/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("bookId") Long bookID,
                                              @RequestBody BookDTO bookDTO) {
        log.info("update book by id {}", bookID);
        BookDTO book = bookService.updateBook(bookID, bookDTO);
        return ResponseEntity.created(URI.create("/api/books" + book.getId())).body(book);
    }

    @DeleteMapping(value = "/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("bookId") Long bookID) {
        log.info("delete book by id {}", bookID);
        bookService.deleteBookById(bookID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") Long bookId) {
        log.info("get book by id {}", bookId);
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/keyword")
    public ResponseEntity<List<BookDTO>> getByBookNameContainingIgnoreCase(@RequestParam("keyword") String keyword) {
        log.info("get book by name containing ignore case {}", keyword);
        return ResponseEntity.ok(bookService.getByBookNameContainingIgnoreCase(keyword));
    }

    @GetMapping("/author_first_name")

    public ResponseEntity<List<BookDTO>> getBookByAuthorFirstName(@RequestParam("authorFirstName") String authorFirstName) {
        return ResponseEntity.ok(bookService.getBookByAuthorFirstName(authorFirstName));
    }

    @GetMapping("/author_last_name")
    public ResponseEntity<List<BookDTO>> getBookByAuthorLastName(@RequestParam("authorLastName") String authorLastName) {
        return ResponseEntity.ok(bookService.getBookByAuthorLastName(authorLastName));
    }

    @GetMapping("/book_summary")
    public ResponseEntity<BookContentDTO> findContentSummaryByBookName(@RequestParam("bookName") String bookName) {
        return ResponseEntity.ok(bookService.findContentSummaryByBookName(bookName));
    }

    @GetMapping("/book_summary_containing")
    public ResponseEntity<BookContentDTO> findContentSummaryByBookNameContaining(@RequestParam("bookName") String bookName) {
        return ResponseEntity.ok(bookService.findContentSummaryByBookNameContaining(bookName));

    }

    @GetMapping("/last_name_containing")
    public ResponseEntity<List<BookDTO>> getBookByAuthorLastNameContaining(@RequestParam("partOfName") String partOfName) {

        return ResponseEntity.ok(bookService.getBookByAuthorLastNameContaining(partOfName));
    }

    @GetMapping("/first_name_containing")
    public ResponseEntity<List<BookDTO>> getBookByAuthorFirstNameContaining(@RequestParam("partOfName") String partOfName) {

        return ResponseEntity.ok(bookService.getBookByAuthorFirstNameContaining(partOfName));
    }

    @GetMapping("/name")
    public ResponseEntity<List<BookDTO>> getByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(bookService.getByName(name));
    }

    @GetMapping("/last_name_containing_ignorecase")
    public ResponseEntity<List<BookDTO>> getBookByAuthorLastNameContainingIgnoreCase(@RequestParam("partOfName") String partOfName) {
        return ResponseEntity.ok(bookService.getBookByAuthorLastNameContainingIgnoreCase(partOfName));
    }

    @GetMapping("/first_name_containing_ignorecase")
    public ResponseEntity<List<BookDTO>> getBookByAuthorFirstNameContainingIgnoreCase(@RequestParam("partOfName") String partOfName) {
        return ResponseEntity.ok(bookService.getBookByAuthorFirstNameContainingIgnoreCase(partOfName));
    }

    @GetMapping("/book_analytic")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookAnalyticDTO>> getBookAnalytic() {
        return ResponseEntity.ok(bookService.getBookAnalytic());
    }

    @GetMapping("/auth_title")
    public ResponseEntity<List<BookDTO>> findBookByNameContaining(@RequestParam String name) {
        return ResponseEntity.ok(bookService.findBookByNameContaining(name));

    }

    @GetMapping("/publish_before_2000")
    public ResponseEntity<List<BookDTO>> getBookPublishBefore2000() {
        return ResponseEntity.ok(bookService.getBookPublishBefore2000());
    }

    @GetMapping("/fn1")
    public ResponseEntity<List<BookDTO>> getBookByAuthorFirstName1(String firstName) {
        return ResponseEntity.ok(bookService.getBookByAuthorFirstName1(firstName));
    }

    @GetMapping("/gn")
    public ResponseEntity<List<BookDTO>> getBookByGenreName(String genreName) {
        return ResponseEntity.ok(bookService.getBookByGenreName(genreName));
    }

    @GetMapping("/crrrrr")
    public ResponseEntity<List<BookDTO>> getBookByCriteria(@RequestParam String firstName, @RequestParam String bookName) {
        return ResponseEntity.ok(bookService.getBookByCriteria(firstName, bookName));
    }
}
