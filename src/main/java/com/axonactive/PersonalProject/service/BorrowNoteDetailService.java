package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;

import java.util.List;

public interface BorrowNoteDetailService {
    List<BorrowNoteDetailDTO> getAllBorrowNoteDetail();

    BorrowNoteDetailDTO createBorrowNoteDetail(BorrowNoteDetailDTO borrowNoteDetailDTO, Long physicalBookID, Long orderID);

    void deleteBorrowNoteDetailByID(Long borrowNoteDetailID);

    BorrowNoteDetailDTO getBorrowNoteDetailId (Long borrowNoteDetailId);

    // 1.Tinh so sach duoc muon boi 1 khach hang cu the
    Long getNumberOfBookByCustomerId(Long customerId);

    Long customerReturnBook(Long customerId, Long numberOfBooksReturned);

    List<String> nameOfBookRemaining (Long customerId, List<Long> physicalBookIds);

    void returnBookByCustomer(Long customerId, List<Long> physicalBookId);
    Long countNumberMaxBorrowBook (Long bookId);

    String getBookNameByBookId (Long bookId);

    List<Long> maxBorrowBook ();

    List<Long> minBorrowBook ();

    List<Book> nameOfMaxBorrowBook();

    List<Book> nameOfMinBorrowBook();

    void feeFineForReturningBookLate(Long customerId,List<Long> bookIds);




//    String getBookNameById (Long bookId);
}
