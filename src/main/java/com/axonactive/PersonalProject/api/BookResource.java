package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Status;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.BookContentDTO;
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
@RequestMapping(value = "/auth/books")

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

    public ResponseEntity<BookDTO> deleteBook(@PathVariable("bookId") Long bookID) {
        bookService.deleteBookById(bookID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/keyword")
    public ResponseEntity<List<BookDTO>> getByBookNameContainingIgnoreCase(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(bookService.getByBookNameContainingIgnoreCase(keyword));
    }
    @GetMapping("/status_available")
    public ResponseEntity<List<BookDTO>> getByStatus (@RequestParam ("status") Status status){
        return ResponseEntity.ok(bookService.getByStatus(status));
    }
    @GetMapping("/pub_house")
    public ResponseEntity<List<BookDTO>> getBookByPublishingHouseName (@RequestParam ("publishingHouseName") String publishingHouseName){
        return ResponseEntity.ok(bookService.getBookByPublishingHouseName(publishingHouseName));
    }
    @GetMapping("/author_first_name")

    public ResponseEntity<List<BookDTO>> getBookByAuthorFirstName (@RequestParam ("authorFirstName") String authorFirstName){
        return ResponseEntity.ok(bookService.getBookByAuthorFirstName(authorFirstName));
    }
    @GetMapping("/author_last_name")
    public ResponseEntity<List<BookDTO>> getBookByAuthorLastName (@RequestParam ("authorLastName") String authorLastName){
        return ResponseEntity.ok(bookService.getBookByAuthorLastName(authorLastName));
    }
    @GetMapping("/book_summary")
    public ResponseEntity <BookContentDTO> findContentSummaryByBookName (@RequestParam ("bookName") String bookName){
        return ResponseEntity.ok(bookService.findContentSummaryByBookName(bookName));
    }




}
