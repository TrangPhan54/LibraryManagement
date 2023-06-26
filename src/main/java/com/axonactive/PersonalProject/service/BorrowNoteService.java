package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteResponseDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;

import java.time.LocalDate;
import java.util.List;

public interface BorrowNoteService {
    List<BorrowNoteDTO> getAllBorrowNote ();
    CreateBorrowNoteResponseDTO createBorrowNote (CreateBorrowNoteDTO createBorrowNoteDTO);
    BorrowNoteDTO updateBorrowNote (Long borrowNoteID, BorrowNoteDTO borrowNoteDTO);
    void deleteBorrowNoteByID (Long borrowNoteID);
    BorrowNoteDTO getBorrowNoteById (Long borrowNoteID);

    List <BorrowNoteDTO> getBorrowNoteHistoryByBorrowDate (LocalDate borrowDate);
    List<String> nameOfCustomerReturnBookLate ();
}
