package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.CustomerWithNumberOfPhysicalCopiesBorrow;
import com.axonactive.PersonalProject.service.dto.customedDto.ReturnBookByCustomerDto;
import jdk.jfr.DataAmount;
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

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping(value = "/{orderId}/{bookId}")
//    public ResponseEntity<BorrowNoteDetailDTO> createBorrowNoteDetail(@PathVariable("orderId") Long orderID,
//                                                                      @PathVariable("bookId") Long bookID,
//                                                                      @RequestBody BorrowNoteDetailDTO borrowNoteDetailDTO) {
//        log.info("create borrow note detail");
//        BorrowNoteDetailDTO book = borrowNoteDetailService.createBorrowNoteDetail(borrowNoteDetailDTO, bookID, orderID);
//        return ResponseEntity.created(URI.create("/api/orderDetails" + book.getId())).body(book);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{orderDetailId}")

    public ResponseEntity<BorrowNoteDetailDTO> deleteBorrowNoteDetail(@PathVariable("orderDetailId") Long orderDetailID) {
        borrowNoteDetailService.deleteBorrowNoteDetailByID(orderDetailID);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowNoteDetailDTO> getBorrowNoteDetailId(@PathVariable("borrowId") Long borrowId) {
        return ResponseEntity.ok(borrowNoteDetailService.getBorrowNoteDetailId(borrowId));
    }

    @GetMapping("/remain")
    public CustomerDTO returnBookByCustomer(@RequestBody ReturnBookByCustomerDto returnBookByCustomerDto) {
        return borrowNoteDetailService.returnBookByCustomer(returnBookByCustomerDto);
    }

    @GetMapping("/max_customer")
    public List<CustomerWithNumberOfPhysicalCopiesBorrow> getMaxCustomer(@RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                                         @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        return borrowNoteDetailService.getMaxCustomer(date1, date2);
    }
}
