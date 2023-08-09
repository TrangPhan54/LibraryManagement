package com.axonactive.PersonalProject.api;


import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth/orderBooks")
public class BorrowNoteResource {
    @Autowired
    private final BorrowNoteService borrowNoteService;

    @GetMapping
    public ResponseEntity<List<BorrowNoteDTO>> getAllBorrowNote() {
        return ResponseEntity.ok(borrowNoteService.getAllBorrowNote());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateBorrowNoteResponseDTO> createBorrowNote(@RequestBody CreateBorrowNoteDTO createBorrowNoteDTO) {
        CreateBorrowNoteResponseDTO book = borrowNoteService.createBorrowNote(createBorrowNoteDTO);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getId())).body(book);
    }

    @PutMapping(value = "/{borrowNoteId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BorrowNoteDTO> updateBorrowNote(@PathVariable("orderBookId") Long borrowNoteID,
                                                         @RequestBody BorrowNoteDTO borrowNoteDTO) {
        BorrowNoteDTO book = borrowNoteService.updateBorrowNote(borrowNoteID, borrowNoteDTO);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getId())).body(book);
    }

    @DeleteMapping(value = "/{borrowAndNoteId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BorrowNoteDTO> deleteBorrowNote(@PathVariable("orderAndBookId") Long borrowAndNoteID) {
        borrowNoteService.deleteBorrowNoteByID(borrowAndNoteID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowNoteDTO> getBorrowNoteById (@PathVariable("borrowId") Long borrowId){
        return ResponseEntity.ok(borrowNoteService.getBorrowNoteById(borrowId));
    }

    @GetMapping("/borrow_date")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <List<BorrowNoteDTO>> getBorrowNoteHistoryByBorrowDate (@RequestParam ("borrowDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate borrowDate){
        return ResponseEntity.ok(borrowNoteService.getBorrowNoteHistoryByBorrowDate(borrowDate));
    }
}