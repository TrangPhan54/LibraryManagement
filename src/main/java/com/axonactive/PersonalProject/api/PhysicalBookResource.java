package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.CustomerService;
import com.axonactive.PersonalProject.service.PhysicalBookService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.PhysicalBookDTO;
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
    public ResponseEntity<PhysicalBookDTO> createPhysicalBook(@RequestBody PhysicalBookDTO physicalBookDTO,
                                                              @PathVariable Long publishingHouseID, @PathVariable Long bookID) {
        PhysicalBookDTO physicalBook = physicalBookService.createPhysicalBook(physicalBookDTO, publishingHouseID, bookID);
        return ResponseEntity.created(URI.create("/api/customers/" + physicalBook.getId())).body(physicalBook);
    }

    @PutMapping(value = "/{physicalBookID}")
    public ResponseEntity<PhysicalBookDTO> updatePhysicalBook(@PathVariable("physicalBookID") Long physicalBookID,
                                                              @RequestBody PhysicalBookDTO physicalBookDTO) {
        PhysicalBookDTO physicalBook = physicalBookService.updatePhysicalBook(physicalBookID, physicalBookDTO);
        return ResponseEntity.created(URI.create("/api/customers" + physicalBook.getId())).body(physicalBook);
    }

    @DeleteMapping
    public ResponseEntity<PhysicalBookDTO> deletePhysicalBookById(@PathVariable("PhysicalBookID") Long physicalBookID) {
        physicalBookService.deletePhysicalBookById(physicalBookID);
        return ResponseEntity.noContent().build();
    }
}
