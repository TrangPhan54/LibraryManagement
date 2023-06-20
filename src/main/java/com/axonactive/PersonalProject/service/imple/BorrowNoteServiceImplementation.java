package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.*;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.BorrowNoteDetailRepository;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteRepository;
import com.axonactive.PersonalProject.repository.PhysicalBookRepository;
import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import com.axonactive.PersonalProject.service.mapper.BorrowNoteBookMapper;
import com.axonactive.PersonalProject.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowNoteServiceImplementation implements BorrowNoteService {
    private final BorrowNoteRepository borrowNoteRepository;
    private final CustomerRepository customerRepository;
    private final BorrowNoteBookMapper borrowNoteBookMapper;
    private final PhysicalBookRepository physicalBookRepository;
    private final CustomerMapper customerMapper;
    private final BorrowNoteDetailRepository borrowNoteDetailRepository;

    @Override
    public List<BorrowNoteDTO> getAllBorrowNote() {
        List<BorrowNote> borrowNotes = borrowNoteRepository.findAll();
        return borrowNoteBookMapper.toDtos(borrowNotes);
    }
    // function: create borrow note and borrow note detail for customer

    @Override
    public BorrowNoteDTO createBorrowNote(CreateBorrowNoteDTO createBorrowNoteDTO) {
//        if (createBorrowNoteDTO.getBorrowDate().isBefore(LocalDate.now())) {
//            throw LibraryException.badRequest("WrongTime", "Ordering Date Must Be After Now");
//        }
//        if (borrowNoteDTO.getAddress().isBlank()) {
//            throw LibraryException.badRequest("WrongAddressFormat", "Address Cannot Be Empty");
//        }
        Customer customer = customerRepository.findById(createBorrowNoteDTO.getCustomerID()).orElseThrow(LibraryException::CustomerNotFound);
        BorrowNote borrowNote = BorrowNote.builder()
                .customer(customer)
                .borrowDate(createBorrowNoteDTO.getBorrowDate())
                .dueDate(createBorrowNoteDTO.getDueDate())
                .build();
        List<BorrowNoteDetail> borrowNoteDetailList = new ArrayList<>();
        for (Long physicalBookId : createBorrowNoteDTO.getPhysicalBookIdList()) {
            BorrowNoteDetail borrowNoteDetail = new BorrowNoteDetail();
            PhysicalBook physicalBook = physicalBookRepository.findById(physicalBookId).orElseThrow(LibraryException::PhysicalBookNotFound);
            borrowNoteDetail.setBorrowNote(borrowNote);
            borrowNoteDetail.setPhysicalBook(physicalBook);
            borrowNoteDetailList.add(borrowNoteDetail);
        }
        borrowNote.setBorrowNoteDetailList(borrowNoteDetailList);
        borrowNote = borrowNoteRepository.save(borrowNote);
        return borrowNoteBookMapper.toDto(borrowNote);
    }

    @Override
    public BorrowNoteDTO updateBorrowNote(Long borrowId, BorrowNoteDTO borrowNoteDTO) {
        BorrowNote borrowNote = borrowNoteRepository.findById(borrowId).orElseThrow(LibraryException::BorrowNoteNotFound);
        if (borrowNoteDTO.getBorrowDate().isBefore(LocalDate.now())) {
            throw LibraryException.badRequest("WrongTime", "Ordering Date Must Be After Now");
        }
        if (borrowNoteDTO.getAddress().isBlank()) {
            throw LibraryException.badRequest("WrongAddressFormat", "Address Cannot Be Empty");
        }
        borrowNote.setBorrowDate(borrowNoteDTO.getBorrowDate());
        borrowNote.setDueDate(borrowNoteDTO.getDueDate());
        borrowNote = borrowNoteRepository.save(borrowNote);
        return borrowNoteBookMapper.toDto(borrowNote);
    }

    @Override
    public void deleteBorrowNoteByID(Long borrowNoteID) {
        BorrowNote borrowNote = borrowNoteRepository.findById(borrowNoteID).orElseThrow(LibraryException::BorrowNoteNotFound);
        borrowNoteRepository.delete(borrowNote);

    }

    @Override
    public BorrowNoteDTO getBorrowNoteById(Long borrowNoteID) {
        return borrowNoteBookMapper.toDto(borrowNoteRepository.findById(borrowNoteID).orElseThrow(LibraryException::BorrowNoteNotFound));
    }

    //Find Borrowing History By Borrow Date

    @Override
    public List<BorrowNoteDTO> getBorrowNoteHistoryByBorrowDate(LocalDate borrowDate) {
        return borrowNoteBookMapper.toDtos(borrowNoteRepository.findBorrowNoteHistoryByBorrowDate(borrowDate));
    }

    // Find Name Of Customer Returning Book Late
    @Override
    public List<String> nameOfCustomerReturnBookLate() {
        return borrowNoteRepository.findAll().stream()
                .filter(br -> LocalDate.now().isAfter(br.getDueDate()))
                .map(BorrowNote::getCustomer)
                .map(Customer::getFirstName)
                .collect(Collectors.toList());
    }
}
