package com.axonactive.PersonalProject.api;


import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
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
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BorrowNoteDTO>> getAllBorrowNote() {
        return ResponseEntity.ok(borrowNoteService.getAllBorrowNote());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BorrowNoteDTO> createBorrowNote(@RequestBody CreateBorrowNoteDTO createBorrowNoteDTO) {
        BorrowNoteDTO book = borrowNoteService.createBorrowNote(createBorrowNoteDTO);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getId())).body(book);
    }

    @PutMapping(value = "/{borrowNoteId}")
    public ResponseEntity<BorrowNoteDTO> updateBorrowNote(@PathVariable("orderBookId") Long borrowNoteID,
                                                         @RequestBody BorrowNoteDTO borrowNoteDTO) {
        BorrowNoteDTO book = borrowNoteService.updateBorrowNote(borrowNoteID, borrowNoteDTO);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getId())).body(book);
    }

    @DeleteMapping(value = "/{borrowAndNoteId}")

    public ResponseEntity<BorrowNoteDTO> deleteBorrowNote(@PathVariable("orderAndBookId") Long borrowAndNoteID) {
        borrowNoteService.deleteBorrowNoteByID(borrowAndNoteID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowNoteDTO> getBorrowNoteById (@PathVariable("borrowId") Long borrowId){
        return ResponseEntity.ok(borrowNoteService.getBorrowNoteById(borrowId));
    }

    @GetMapping("/borrow_date")
    public ResponseEntity <List<BorrowNoteDTO>> getBorrowNoteHistoryByBorrowDate (@RequestParam ("borrowDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate borrowDate){
        return ResponseEntity.ok(borrowNoteService.getBorrowNoteHistoryByBorrowDate(borrowDate));
    }
}