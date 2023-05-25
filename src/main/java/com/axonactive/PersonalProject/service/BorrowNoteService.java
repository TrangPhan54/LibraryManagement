package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;

import java.time.LocalDate;
import java.util.List;

public interface BorrowNoteService {
    List<BorrowNoteDTO> getAllBorrowNote ();
    BorrowNoteDTO createBorrowNote (BorrowNoteDTO borrowNoteDTO, Long customerID);
    BorrowNoteDTO updateBorrowNote (Long borrowNoteID, BorrowNoteDTO borrowNoteDTO);
    void deleteBorrowNoteByID (Long borrowNoteID);
    BorrowNoteDTO getBorrowNoteById (Long borrowNoteID);

    List <BorrowNoteDTO> getBorrowNoteHistoryByBorrowDate (LocalDate borrowDate);


}
