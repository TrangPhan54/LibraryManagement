package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.*;

import java.time.LocalDate;
import java.util.List;

public interface BorrowNoteDetailService {
    List<BorrowNoteDetailDTO> getAllBorrowNoteDetail();

    BorrowNoteDetailDTO createBorrowNoteDetail(CreateBorrowNoteDetailDTO createBorrowNoteDetailDTO);

    void deleteBorrowNoteDetailByID(Long borrowNoteDetailID);

    BorrowNoteDetailDTO getBorrowNoteDetailId(Long borrowNoteDetailId);

    // 1.Tinh so sach duoc muon boi 1 khach hang cu the
    Long getNumberOfBookByCustomerId(Long customerId);

    Long customerReturnBook(Long customerId, Long numberOfBooksReturned);

    List<String> nameOfBookRemaining(Long customerId, List<Long> physicalBookIds);

    //    void returnBookByCustomer(Long customerId, List<Long> physicalBookId);
    CustomerDTO banAccountForReturningBookLate(ReturnBookByCustomerDto returnBookByCustomerDto);
    FineFeeForCustomerDTO fineFeeForReturningBookLate(ReturnBookByCustomerDto returnBookByCustomerDto);

    List<BookAnalyticForAmountOfTimeDTO> getMaxBorrowBook(LocalDate date1, LocalDate date2);

    List<CustomerWithNumberOfPhysicalCopiesBorrowDTO> getMaxCustomer(LocalDate date1, LocalDate date2);

    FineFeeForCustomerDTO lostBook(ReturnBookByCustomerDto returnBookByCustomerDto);

    List<BorrowNoteDetail> getBookListOfACustomer1(Long customerID);

    List<BorrowNoteDetail> getBookListOfACustomer(Long customerID);

    List<BorrowNoteDetailDTO> getBorowNoteDetailByBorrowNoteID (Long borrowID);

    List<BorrowNoteDetailDTO> getBookListOfACustomer2(Long customerID);

    List<BorrowNoteDetailDTO> getListOfCustomerStillBorrowBook2();

    List<CustomerDTO> getListOfCustomerStillBorrowBook3();


//    List<BorrowNoteDetail> getBookListOfACustomer(ReturnBookByCustomerDto returnBookByCustomerDto);


//    List<CustomerDTO> getMaxBorrowCustomer (LocalDate date1, LocalDate date2);


//    String getBookNameById (Long bookId);
}
