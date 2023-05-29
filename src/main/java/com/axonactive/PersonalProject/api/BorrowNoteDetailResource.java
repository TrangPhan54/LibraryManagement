package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
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
@RequestMapping(value = "/auth/orderDetails")
public class BorrowNoteDetailResource {
    @Autowired
    private final BorrowNoteDetailService borrowNoteDetailService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BorrowNoteDetailDTO>> getAllBorrowNotedetail() {
        log.info("get all borrow note detail");
        return ResponseEntity.ok(borrowNoteDetailService.getAllBorrowNoteDetail());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/{orderId}/{bookId}")
    public ResponseEntity<BorrowNoteDetailDTO> createBorrowNoteDetail(@PathVariable("orderId") Long orderID,
                                                                     @PathVariable ("bookId") Long bookID,
                                                                     @RequestBody BorrowNoteDetailDTO borrowNoteDetailDTO) {
        log.info("create borrow note detail");
        BorrowNoteDetailDTO book = borrowNoteDetailService.createBorrowNoteDetail(borrowNoteDetailDTO,bookID,orderID);
        return ResponseEntity.created(URI.create("/api/orderDetails" + book.getId())).body(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{orderDetailId}")

    public ResponseEntity<BorrowNoteDetailDTO> deleteBorrowNoteDetail(@PathVariable("orderDetailId") Long orderDetailID) {
        borrowNoteDetailService.deleteBorrowNoteDetailByID(orderDetailID);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowNoteDetailDTO>getBorrowNoteDetailId(@PathVariable ("borrowId") Long borrowId){
        return ResponseEntity.ok(borrowNoteDetailService.getBorrowNoteDetailId(borrowId));
    }
}
