package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CreateReceiptDTO;
import com.axonactive.PersonalProject.service.dto.ReceiptDTO;

import java.util.List;

public interface ReceiptService {
    List<ReceiptDTO> getAllReceipt ();
    ReceiptDTO createReceipt (CreateReceiptDTO createReceiptDTO);
}
