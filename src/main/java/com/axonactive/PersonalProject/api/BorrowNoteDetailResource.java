package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.BookAnalyticForAmountOfTimeDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.CustomerWithNumberOfPhysicalCopiesBorrowDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.FineFeeForCustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.ReturnBookByCustomerDTO;
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
    public CustomerDTO returnBookByCustomer(@RequestBody ReturnBookByCustomerDTO returnBookByCustomerDto) {
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
}
