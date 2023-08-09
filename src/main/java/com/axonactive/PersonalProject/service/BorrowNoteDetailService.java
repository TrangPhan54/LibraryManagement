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
    void deleteBorrowNoteDetailByID(Long borrowNoteDetailID);

    BorrowNoteDetailDTO getBorrowNoteDetailId(Long borrowNoteDetailId);

    // 1.Tinh so sach duoc muon boi 1 khach hang cu the
    Long getNumberOfBookByCustomerId(Long customerId);

    CustomerDTO banAccountForReturningBookLate(ReturnBookByCustomerDTO returnBookByCustomerDto);

    CustomerDTO banAccountForReturningBookLate1(ReturnBookByCustomerDTO returnBookByCustomerDto);

    FineFeeForCustomerDTO fineFeeForReturningBookLate(ReturnBookByCustomerDTO returnBookByCustomerDto);

    List<BookAnalyticForAmountOfTimeDTO> getMaxBorrowBook(LocalDate date1, LocalDate date2);

    List<CustomerWithNumberOfPhysicalCopiesBorrowDTO> getMaxCustomer(LocalDate date1, LocalDate date2);

    FineFeeForCustomerDTO lostBook(ReturnBookByCustomerDTO returnBookByCustomerDto);

    List<BorrowNoteDetail> getBookListOfACustomer1(Long customerID);

    List<BorrowNoteDetail> getBookListOfACustomer(Long customerID);

    List<BorrowNoteDetailDTO> getBorowNoteDetailByBorrowNoteID(Long borrowID);

    List<BorrowNoteDetailDTO> getBookListOfACustomer2(Long customerID);

    List<BorrowNoteDetailDTO> getListOfCustomerStillBorrowBook2();

    List<CustomerDTO> getListOfCustomerOwnBook();

}
