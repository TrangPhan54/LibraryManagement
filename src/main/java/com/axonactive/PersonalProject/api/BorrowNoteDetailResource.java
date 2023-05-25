package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
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
@RequestMapping(value = "/api/orderDetails")
public class BorrowNoteDetailResource {
    @Autowired
    private final BorrowNoteDetailService borrowNoteDetailService;

    @GetMapping
    public ResponseEntity<List<BorrowNoteDetailDTO>> getAllOrderBookdetail() {
        return ResponseEntity.ok(borrowNoteDetailService.getAllOrderDetail());
    }


    @PostMapping(value = "/{orderId}/{bookId}")
    public ResponseEntity<BorrowNoteDetailDTO> createOrderBookDetail(@PathVariable("orderId") Long orderID,
                                                                     @PathVariable ("bookId") Long bookID,
                                                                     @RequestBody BorrowNoteDetailDTO borrowNoteDetailDTO) {
        BorrowNoteDetailDTO book = borrowNoteDetailService.createOrderDetail(borrowNoteDetailDTO,bookID,orderID);
        return ResponseEntity.created(URI.create("/api/orderDetails" + book.getBorrowDetailID())).body(book);
    }


    @DeleteMapping(value = "/{orderDetailId}")

    public ResponseEntity<BorrowNoteDetailDTO> deleteOrderBookDetail(@PathVariable("orderDetailId") Long orderDetailID) {
        borrowNoteDetailService.deleteOrderDetailByID(orderDetailID);
        return ResponseEntity.noContent().build();
    }
}
