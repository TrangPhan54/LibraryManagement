package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;

import java.util.List;

public interface BorrowNoteService {
    List<BorrowNoteDTO> getAllOrder ();
    BorrowNoteDTO createOrder (BorrowNoteDTO borrowNoteDTO, Long customerID);
    BorrowNoteDTO updateOrder (Long orderID, BorrowNoteDTO borrowNoteDTO);
    void deleteOrderByID (Long orderID);
    BorrowNoteDTO getOrderById (Long orderID);
}
