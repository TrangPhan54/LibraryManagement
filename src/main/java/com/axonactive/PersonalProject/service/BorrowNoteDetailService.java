package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;

import java.util.List;

public interface BorrowNoteDetailService {
    List<BorrowNoteDetailDTO> getAllOrderDetail ();
    BorrowNoteDetailDTO createOrderDetail (BorrowNoteDetailDTO borrowNoteDetailDTO, Long bookID, Long orderID);
//    OrderDetailDTO updateOrderDetail (Long orderDetailID, OrderDetailDTO orderBookDTO);
    void deleteOrderDetailByID (Long orderDetailID);
}
