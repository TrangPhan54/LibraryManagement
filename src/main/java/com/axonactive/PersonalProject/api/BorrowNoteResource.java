package com.axonactive.PersonalProject.api;


import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
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
@RequestMapping(value = "/api/orderBooks")
public class BorrowNoteResource {
    @Autowired
    private final BorrowNoteService borrowNoteService;

    @GetMapping
    public ResponseEntity<List<BorrowNoteDTO>> getAllOrderBook() {
        return ResponseEntity.ok(borrowNoteService.getAllOrder());
    }


    @PostMapping(value = "/{customerId}")
    public ResponseEntity<BorrowNoteDTO> createOrderBook(@PathVariable("customerId") Long customerID,
                                                         @RequestBody BorrowNoteDTO borrowNoteDTO) {
        BorrowNoteDTO book = borrowNoteService.createOrder(borrowNoteDTO,customerID);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getBorrowID())).body(book);
    }

    @PutMapping(value = "/{orderBookId}")
    public ResponseEntity<BorrowNoteDTO> updateOrderBook(@PathVariable("orderBookId") Long orderBookID,
                                                         @RequestBody BorrowNoteDTO borrowNoteDTO) {
        BorrowNoteDTO book = borrowNoteService.updateOrder(orderBookID, borrowNoteDTO);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getBorrowID())).body(book);
    }

    @DeleteMapping(value = "/{orderAndBookId}")

    public ResponseEntity<GenreBookDTO> deleteOrderBook(@PathVariable("orderAndBookId") Long orderAndBookID) {
        borrowNoteService.deleteOrderByID(orderAndBookID);
        return ResponseEntity.noContent().build();
    }
}