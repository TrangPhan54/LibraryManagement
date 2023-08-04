package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Status;
import com.axonactive.PersonalProject.service.PhysicalBookService;
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
@RequestMapping(value = "/auth/physicalBooks")
@RequiredArgsConstructor
public class PhysicalBookResource {
    @Autowired
    private final PhysicalBookService physicalBookService;

    @GetMapping
    public ResponseEntity<List<PhysicalBookDTO>> getAllPhysicalBook() {
        return ResponseEntity.ok(physicalBookService.getAllPhysicalBook());
    }

    @PostMapping
    public ResponseEntity<PhysicalBookDTO> createPhysicalBook(@RequestBody CreatePhysicalBookDTO createPhysicalBookDto) {
        PhysicalBookDTO physicalBook = physicalBookService.createPhysicalBook(createPhysicalBookDto);
        return ResponseEntity.created(URI.create("/api/customers/" + physicalBook.getId())).body(physicalBook);
    }

    @PutMapping
    public ResponseEntity<PhysicalBookDTO> updatePhysicalBook(@RequestBody PhysicalBookDTO physicalBookDTO) {
        PhysicalBookDTO physicalBook = physicalBookService.updatePhysicalBook(physicalBookDTO);
        return ResponseEntity.created(URI.create("/api/customers" + physicalBook.getId())).body(physicalBook);
    }

    @DeleteMapping
    public ResponseEntity<PhysicalBookDTO> deletePhysicalBookById(@PathVariable("PhysicalBookID") Long physicalBookID) {
        physicalBookService.deletePhysicalBookById(physicalBookID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/liquid")
    public ResponseEntity<List<PhysicalBookDTO>> getLiquidationBook() {
        return ResponseEntity.ok(physicalBookService.getLiquidationBook());

    }
    @GetMapping("/pub_name")
    public  ResponseEntity<List<PhysicalBookDTO>> findPhysicalBookByPublishingHouseName(@RequestParam String publishingHouseName){
        return ResponseEntity.ok(physicalBookService.findPhysicalBookByPublishingHouseName(publishingHouseName));

    }

    @GetMapping("/status")
    public ResponseEntity<List<PhysicalBookDTO>> getByStatus(Status status) {
        return ResponseEntity.ok(physicalBookService.getByStatus(status));
    }

    @GetMapping("/find_all_by_id")

    public ResponseEntity<List<PhysicalBookDTO>> findAllById(@RequestBody ListOfPhysicalBookDTO listOfPhysicalBookDTO) {
        return ResponseEntity.ok(physicalBookService.findAllById(listOfPhysicalBookDTO));
    }


    }


