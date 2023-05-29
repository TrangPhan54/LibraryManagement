package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;

import java.util.List;

public interface BorrowNoteDetailService {
    List<BorrowNoteDetailDTO> getAllBorrowNoteDetail();

    BorrowNoteDetailDTO createBorrowNoteDetail(BorrowNoteDetailDTO borrowNoteDetailDTO, Long bookID, Long orderID);

    void deleteBorrowNoteDetailByID(Long borrowNoteDetailID);

    BorrowNoteDetailDTO getBorrowNoteDetailId (Long borrowNoteDetailId);

    // 1.Tinh so sach duoc muon boi 1 khach hang cu the
    Long getNumberOfBookByCustomerId(Long customerId);

    Long customerReturnBook(Long customerId, Long numberOfBooksReturned);

    List<String> nameOfBookRemaining (Long customerId, Long bookId);

    void returnBookByCustomer(Long customerId, Long bookId);
    Long countNumberMaxBorrowBook (Long bookId);

    String getBookNameByBoodId (Long bookId);

    List<Long> maxBorrowBook ();

    List<Long> minBorrowBook ();

    List<Book> nameOfMaxBorrowBook();

    List<Book> nameOfMinBorrowBook();




//    String getBookNameById (Long bookId);
}
