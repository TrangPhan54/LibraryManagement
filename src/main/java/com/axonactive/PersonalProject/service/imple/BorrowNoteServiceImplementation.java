package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteRepository;
import com.axonactive.PersonalProject.service.BorrowNoteService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import com.axonactive.PersonalProject.service.mapper.BorrowNoteBookMapper;
import com.axonactive.PersonalProject.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowNoteServiceImplementation implements BorrowNoteService {
    private final BorrowNoteRepository borrowNoteRepository;
    private final CustomerRepository customerRepository;
    private final BorrowNoteBookMapper borrowNoteBookMapper;
    private final CustomerMapper customerMapper;
    @Override
    public List<BorrowNoteDTO> getAllBorrowNote() {
        List<BorrowNote> borrowNotes = borrowNoteRepository.findAll();
        return borrowNoteBookMapper.toDtos(borrowNotes);
    }

    @Override
    public BorrowNoteDTO createBorrowNote(BorrowNoteDTO borrowNoteDTO, Long customerID) {
        if (borrowNoteDTO.getBorrowDate().isBefore(LocalDate.now())){
            throw LibraryException.badRequest("WrongTime","Ordering Date Must Be After Now");
        }
        if (borrowNoteDTO.getAddress().isBlank()){
            throw LibraryException.badRequest("WrongAddressFormat","Address Cannot Be Empty");
        }
        BorrowNote borrowNote = new BorrowNote();
        Customer customer = customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound);
        borrowNote.setBorrowDate(borrowNoteDTO.getBorrowDate());
        borrowNote.setAddress(borrowNoteDTO.getAddress());
        borrowNote.setCustomer(customer);
        borrowNote = borrowNoteRepository.save(borrowNote);
        return borrowNoteBookMapper.toDto(borrowNote);


    }

    @Override
    public BorrowNoteDTO updateBorrowNote(Long borrowId, BorrowNoteDTO borrowNoteDTO) {
        BorrowNote borrowNote = borrowNoteRepository.findById(borrowId).orElseThrow(LibraryException::BorrowNoteNotFound);
        if (borrowNoteDTO.getBorrowDate().isBefore(LocalDate.now())){
            throw LibraryException.badRequest("WrongTime","Ordering Date Must Be After Now");
        }
        if (borrowNoteDTO.getAddress().isBlank()){
            throw LibraryException.badRequest("WrongAddressFormat","Address Cannot Be Empty");
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

    //1.Tim lich su muon sach dua vao ngay cho muon

    @Override
    public List<BorrowNoteDTO> getBorrowNoteHistoryByBorrowDate(LocalDate borrowDate) {
        return borrowNoteBookMapper.toDtos(borrowNoteRepository.findBorrowNoteHistoryByBorrowDate(borrowDate));
    }
    // tim id nhung khach hang muon sach nhieu nhat
    @Override
    public List<Long> maxBorrowCustomer() {
        return borrowNoteRepository.maxBorrowCustomer();
    }
    // tim ten nhung khach hang muon sach nhieu nhat
    @Override
    public List<CustomerDTO> nameOfMaxBorrowCustomer() {
        return customerMapper.toDtos(borrowNoteRepository.nameOfMaxBorrowCustomer(maxBorrowCustomer()));
    }
    // tim id nhung khach hang muon sach it nhat

    @Override
    public List<Long> minBorrowCustomer() {
        return borrowNoteRepository.minBorrowCustomer();
    }
    // tim ten nhung khach hang muon sach it nhat
    @Override
    public List<CustomerDTO> nameOfMinBorrowCustomer() {
        return customerMapper.toDtos(borrowNoteRepository.nameOfMinBorrowCustomer(minBorrowCustomer()));
    }
    // tim ten nhung khach hang tra sach tre
    @Override
    public List<String> nameOfCustomerReturnBookLate() {
       return borrowNoteRepository.findAll().stream()
               .filter(br -> LocalDate.now().isAfter(br.getDueDate()))
               .map(BorrowNote::getCustomer)
               .map(Customer::getFirstName)
               .collect(Collectors.toList());
    }
}
