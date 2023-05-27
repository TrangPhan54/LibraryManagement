package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.axonactive.PersonalProject.service.BookPublishService;
import com.axonactive.PersonalProject.service.BookService;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.BookPublishDTO;
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
@RequestMapping(value = "/auth/bookPublishes")
public class BookPublishResource {
    @Autowired
    private final BookPublishService bookPublishService;

    @GetMapping
    public ResponseEntity<List<BookPublishDTO>> getAllBookPublish() {
        return ResponseEntity.ok(bookPublishService.getAllBookPublish());
    }

    @PostMapping(value = "/{bookId}/{publishingHouseId}")
    public ResponseEntity<BookPublishDTO> createBookPublish(@PathVariable("bookId") Long bookID,
                                              @PathVariable("publishingHouseId") Long publishingHouseID,
                                              @RequestBody BookPublishDTO bookPublishDTO) {
        BookPublishDTO book = bookPublishService.createBookPublish(bookPublishDTO, publishingHouseID, bookID);
        return ResponseEntity.created(URI.create("/api/bookPublishes" + book.getBookPublishID())).body(book);
    }

    @PutMapping(value = "/{bookPubId}")
    public ResponseEntity<BookPublishDTO> updateBookPublish (@PathVariable("bookPubId") Long bookPublishID,
                                              @RequestBody BookPublishDTO bookPublishDTO) {
        BookPublishDTO book = bookPublishService.updateBookPublish(bookPublishID,bookPublishDTO);
        return ResponseEntity.created(URI.create("/api/bookPublishes" + book.getBookPublishID())).body(book);
    }
    @DeleteMapping(value = "/{bookPubLishHouseId}")

    public ResponseEntity<BookPublishDTO> deleteBookPublish (@PathVariable("bookPubLishHouseId") Long bookPubID){
        bookPublishService.deleteBookPublishById(bookPubID);
        return ResponseEntity.noContent().build();
    }
//
//    @GetMapping("/book_publish")
//    public ResponseEntity <List<BookPublishDTO>> findByPublishingHouse (@RequestParam ("publishingHouse") PublishingHouse publishingHouse){
//        return ResponseEntity.ok(bookPublishService.findByPublishingHouse(publishingHouse));
//    }
}
