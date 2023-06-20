package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.ReceiptService;
import com.axonactive.PersonalProject.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/auth/receipts")
@RequiredArgsConstructor
public class ReceiptResource {
    @Autowired
    private final ReceiptService receiptService;
    @GetMapping
    public ResponseEntity<List<ReceiptDTO>> getAllReceipt() {
        return ResponseEntity.ok(receiptService.getAllReceipt());
    }
//    @PostMapping
//    public ResponseEntity<ReceiptDTO> createReceipt(@RequestBody ListOfPhysicalBookDTO listOfPhysicalBookDTO, Long customerID) {
//        ReceiptDTO receipt = receiptService.createReceipt(listOfPhysicalBookDTO,customerID);
//        return ResponseEntity.created(URI.create("/auth/receipts" + receipt.getId())).body(receipt);
//    }
    @PostMapping
    public ResponseEntity<ReceiptDTO> createReceipt(@RequestBody CreateReceiptDTO createReceiptDTO) {
        ReceiptDTO receipt = receiptService.createReceipt(createReceiptDTO);
        return ResponseEntity.created(URI.create("/auth/receipts" + receipt.getId())).body(receipt);
    }

}
