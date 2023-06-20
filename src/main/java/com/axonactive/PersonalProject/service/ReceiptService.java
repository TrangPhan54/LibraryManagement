package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.*;

import java.util.List;

public interface ReceiptService {
    List<ReceiptDTO> getAllReceipt ();
    ReceiptDTO createReceipt (CreateReceiptDTO createReceiptDTO);

//    ReceiptDTO createReceipt(ListOfPhysicalBookDTO listOfPhysicalBookDTO, Long customerID);
}
