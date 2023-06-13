package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    Map<Book, Long> getMaxBorrowBook (LocalDate date1 , LocalDate date2);

    Map<Customer,Long> getMaxCustomer(LocalDate date1, LocalDate date2);


//    List<CustomerDTO> getMaxBorrowCustomer (LocalDate date1, LocalDate date2);




//    String getBookNameById (Long bookId);
}
