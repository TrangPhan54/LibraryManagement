package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.Status;
import com.axonactive.PersonalProject.service.dto.CreatePhysicalBookDTO;
import com.axonactive.PersonalProject.service.dto.ListOfPhysicalBookDTO;
import com.axonactive.PersonalProject.service.dto.PhysicalBookDTO;
import java.util.List;

public interface PhysicalBookService {
    List<PhysicalBookDTO> getAllPhysicalBook();

    PhysicalBookDTO createPhysicalBook(CreatePhysicalBookDTO createPhysicalBookDto);

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



}
