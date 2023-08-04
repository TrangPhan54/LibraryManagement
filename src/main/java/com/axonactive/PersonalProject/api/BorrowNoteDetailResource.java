package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth/orderDetails")
public class BorrowNoteDetailResource {
    @Autowired
    private final BorrowNoteDetailService borrowNoteDetailService;


    @GetMapping
    public ResponseEntity<List<BorrowNoteDetailDTO>> getAllBorrowNotedetail() {
        return ResponseEntity.ok(borrowNoteDetailService.getAllBorrowNoteDetail());
    }

    @DeleteMapping(value = "/{orderDetailId}")

    public ResponseEntity<BorrowNoteDetailDTO> deleteBorrowNoteDetail(@PathVariable("orderDetailId") Long orderDetailID) {
        borrowNoteDetailService.deleteBorrowNoteDetailByID(orderDetailID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowNoteDetailDTO> getBorrowNoteDetailId(@PathVariable("borrowId") Long borrowId) {
        return ResponseEntity.ok(borrowNoteDetailService.getBorrowNoteDetailId(borrowId));
    }

    @GetMapping("/remain")
    public ResponseEntity<CustomerDTO> banAccountForReturningBookLate(@RequestBody ReturnBookByCustomerDTO returnBookByCustomerDto) {
        return ResponseEntity.ok(borrowNoteDetailService.banAccountForReturningBookLate(returnBookByCustomerDto));
    }

    @GetMapping("/max_customer")
    public ResponseEntity<List<CustomerWithNumberOfPhysicalCopiesBorrowDTO>> getMaxCustomer(@RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                                                            @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        return ResponseEntity.ok(borrowNoteDetailService.getMaxCustomer(date1, date2));
    }

    @GetMapping("/fine_fee")
    public ResponseEntity<FineFeeForCustomerDTO> fineFeeForReturningBookLate(@RequestBody ReturnBookByCustomerDTO returnBookByCustomerDto) {
        return ResponseEntity.ok(borrowNoteDetailService.fineFeeForReturningBookLate(returnBookByCustomerDto));
    }

    @GetMapping("/book_analytic")
    public ResponseEntity<List<BookAnalyticForAmountOfTimeDTO>> getMaxBorrowBook(@RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                                                 @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        return ResponseEntity.ok(borrowNoteDetailService.getMaxBorrowBook(date1, date2));
    }

    @GetMapping("/lost_book")
    public ResponseEntity<FineFeeForCustomerDTO> lostBook(@RequestBody ReturnBookByCustomerDTO returnBookByCustomerDto) {
        return ResponseEntity.ok(borrowNoteDetailService.lostBook(returnBookByCustomerDto));
    }

    @GetMapping("/cus1")
    public ResponseEntity<List<BorrowNoteDetail>> getBookListOfACustomer1(@RequestParam Long customerID) {
        return ResponseEntity.ok(borrowNoteDetailService.getBookListOfACustomer1(customerID));
    }

    @GetMapping("/borrowNote")
    public ResponseEntity<List<BorrowNoteDetailDTO>> getBorowNoteDetailByBorrowNoteID(@RequestParam Long borrowID) {
        return ResponseEntity.ok(borrowNoteDetailService.getBorowNoteDetailByBorrowNoteID(borrowID));
    }

    @GetMapping("/br_id")
    public ResponseEntity<List<BorrowNoteDetailDTO>> getBookListOfACustomer2(@RequestParam Long customerID) {
        return ResponseEntity.ok(borrowNoteDetailService.getBookListOfACustomer2(customerID));
    }

    @GetMapping("/null2")
    public ResponseEntity<List<BorrowNoteDetailDTO>> getListOfCustomerStillBorrowBook2() {
        return ResponseEntity.ok(borrowNoteDetailService.getListOfCustomerStillBorrowBook2());
    }

    @GetMapping("/null3")
    public ResponseEntity<List<CustomerDTO>> getListOfCustomerStillBorrowBook3() {
        return ResponseEntity.ok(borrowNoteDetailService.getListOfCustomerStillBorrowBook3());
    }

    @GetMapping("/own")
    public ResponseEntity<List<CustomerDTO>> getListOfCustomerOwnBook() {
        return ResponseEntity.ok(borrowNoteDetailService.getListOfCustomerOwnBook());

    }
}
