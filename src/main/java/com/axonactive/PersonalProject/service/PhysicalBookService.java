package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.PhysicalBook;
import com.axonactive.PersonalProject.entity.Status;
import com.axonactive.PersonalProject.service.dto.*;

import java.util.List;
import java.util.function.Predicate;

public interface PhysicalBookService {
    List<PhysicalBookDTO> getAllPhysicalBook();

    PhysicalBookDTO createPhysicalBook(CreatePhysicalBookDto createPhysicalBookDto);

    PhysicalBookDTO updatePhysicalBook(PhysicalBookDTO bookDTO);

    void deletePhysicalBookById(Long physicalBookID);
    //find physical book base on publishing house
    List<PhysicalBookDTO> findPhysicalBookByPublishingHouseName (String publishingHouseName);
    // count book base on book name (same title)
    Long countBookBaseOnBookName (String bookName);
    // get list of books that have condition liquidation
    List<PhysicalBookDTO> getLiquidationBook ();

    List<PhysicalBookDTO> getByStatus (Status status);

    List<PhysicalBookDTO> findAllById(ListOfPhysicalBookDTO listOfPhysicalBookDTO);
    ReturnPhysicalBookDTO returnPhysicalBook(Long id);
}
