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
    public CustomerDTO banAccountForReturningBookLate(@RequestBody ReturnBookByCustomerDTO returnBookByCustomerDto) {
        return borrowNoteDetailService.banAccountForReturningBookLate(returnBookByCustomerDto);
    }

    @GetMapping("/max_customer")
    public List<CustomerWithNumberOfPhysicalCopiesBorrowDTO> getMaxCustomer(@RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                                            @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        return borrowNoteDetailService.getMaxCustomer(date1, date2);
    }
    @GetMapping("/fine_fee")
    public FineFeeForCustomerDTO fineFeeForReturningBookLate (@RequestBody ReturnBookByCustomerDTO returnBookByCustomerDto){
        return borrowNoteDetailService.fineFeeForReturningBookLate(returnBookByCustomerDto);
    }
    @GetMapping("/book_analytic")
    public List<BookAnalyticForAmountOfTimeDTO> getMaxBorrowBook (@RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                                  @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2){
        return borrowNoteDetailService.getMaxBorrowBook(date1, date2);
    }

    @GetMapping("/lost_book")
    public FineFeeForCustomerDTO lostBook(@RequestBody ReturnBookByCustomerDTO returnBookByCustomerDto) {
        return borrowNoteDetailService.lostBook(returnBookByCustomerDto);
    }

    @GetMapping("/cus1")
    public List<BorrowNoteDetail> getBookListOfACustomer1(@RequestParam Long customerID){
        return borrowNoteDetailService.getBookListOfACustomer1(customerID);
    }
    @GetMapping("/borrowNote")
    public List<BorrowNoteDetailDTO> getBorowNoteDetailByBorrowNoteID (@RequestParam Long borrowID){
        return borrowNoteDetailService.getBorowNoteDetailByBorrowNoteID(borrowID);
    }
    @GetMapping("/br_id")
    public List<BorrowNoteDetailDTO> getBookListOfACustomer2(@RequestParam Long customerID){
        return borrowNoteDetailService.getBookListOfACustomer2(customerID);
    }

    @GetMapping("/null2")
    public List<BorrowNoteDetailDTO> getListOfCustomerStillBorrowBook2(){
        return borrowNoteDetailService.getListOfCustomerStillBorrowBook2();
    }
    @GetMapping("/null3")
    public List<CustomerDTO> getListOfCustomerStillBorrowBook3(){
        return borrowNoteDetailService.getListOfCustomerStillBorrowBook3();
    }
}
