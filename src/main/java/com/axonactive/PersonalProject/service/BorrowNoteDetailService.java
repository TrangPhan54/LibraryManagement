package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.BookAnalyticForAmountOfTimeDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.CustomerWithNumberOfPhysicalCopiesBorrowDTO;
import com.axonactive.PersonalProject.service.dto.customedDto.FineFeeForCustomerDTO;
//import com.axonactive.PersonalProject.service.dto.customedDto.ReturnBookByCustomerDto;

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
//    CustomerDTO banAccountForReturningBookLate(ReturnBookByCustomerDto returnBookByCustomerDto);

    String getBookNameByBookId(Long bookId);

//    FineFeeForCustomerDTO fineFeeForReturningBookLate(ReturnBookByCustomerDto returnBookByCustomerDto);

    List<BookAnalyticForAmountOfTimeDTO> getMaxBorrowBook(LocalDate date1, LocalDate date2);

    List<CustomerWithNumberOfPhysicalCopiesBorrowDTO> getMaxCustomer(LocalDate date1, LocalDate date2);

//    FineFeeForCustomerDTO lostBook(ReturnBookByCustomerDto returnBookByCustomerDto);


//    List<CustomerDTO> getMaxBorrowCustomer (LocalDate date1, LocalDate date2);


//    String getBookNameById (Long bookId);
    List<BorrowNoteDetailDTO> getBorrowNoteDetailListByBorrowNoteId(Long id);
}
