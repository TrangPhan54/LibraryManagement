package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.*;
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
    public ResponseEntity<BookDTO> createBook(@RequestBody CreateBookDTO createBookDTO) {
        log.info("create book");
        BookDTO book = bookService.createBook(createBookDTO);
        return ResponseEntity.created(URI.create("/api/books" + book.getId())).body(book);
    }

    @PutMapping(value = "/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("bookId") Long bookID,
                                              @RequestBody BookDTO bookDTO) {
        log.info("update book by id {}",bookID);
        BookDTO book = bookService.updateBook(bookID, bookDTO);
        return ResponseEntity.created(URI.create("/api/books" + book.getId())).body(book);
    }

    @DeleteMapping(value = "/{bookId}")

    public ResponseEntity<BookDTO> deleteBook(@PathVariable("bookId") Long bookID) {
        log.info("delete book by id {}",bookID);
        bookService.deleteBookById(bookID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") Long bookId) {
        log.info("get book by id {}",bookId);
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/keyword")
    public ResponseEntity<List<BookDTO>> getByBookNameContainingIgnoreCase(@RequestParam("keyword") String keyword) {
        log.info("get book by name containing ignore case {}",keyword);
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
    public List<BookAnalyticDTO> getBookAnalytic(){
        return bookService.getBookAnalytic();
    }
    @GetMapping("/auth_title")
    public List<BookDTO> findBookByNameContaining(@RequestParam String name) {
        return bookService.findBookByNameContaining(name);

    }
    @GetMapping("/publish_before_2000")
    public List<BookDTO> getBookPublishBefore2000() {
        return bookService.getBookPublishBefore2000();
    }
    @GetMapping("/fn1")
    public List<BookDTO> getBookByAuthorFirstName1 (String firstName){
        return bookService.getBookByAuthorFirstName1(firstName);
    }
    @GetMapping("/gn")
    public List<BookDTO> getBookByGenreName (String genreName){
        return bookService.getBookByGenreName(genreName);
    }





}
