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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowNoteServiceImplementation implements BorrowNoteService {
    private final BorrowNoteRepository borrowNoteRepository;
    private final CustomerRepository customerRepository;
    private final BorrowNoteBookMapper borrowNoteBookMapper;
    @Override
    public List<BorrowNoteDTO> getAllOrder() {
        List<BorrowNote> borrowNotes = borrowNoteRepository.findAll();
        return borrowNoteBookMapper.toDtos(borrowNotes);
    }

    @Override
    public BorrowNoteDTO createOrder(BorrowNoteDTO borrowNoteDTO, Long customerID) {
        BorrowNote borrowNote = new BorrowNote();
        Customer customer = customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound);
        borrowNote.setBorrowDate(borrowNoteDTO.getBorrowDate());
        borrowNote.setAddress(borrowNoteDTO.getAddress());
        borrowNote.setCustomer(customer);
        borrowNote = borrowNoteRepository.save(borrowNote);
        return borrowNoteBookMapper.toDto(borrowNote);


    }

    @Override
    public BorrowNoteDTO updateOrder(Long borrowId, BorrowNoteDTO borrowNoteDTO) {
        BorrowNote borrowNote = borrowNoteRepository.findById(borrowId).orElseThrow(LibraryException::OrderNotFound);
        borrowNote.setBorrowDate(borrowNoteDTO.getBorrowDate());
        borrowNote.setDueDate(borrowNoteDTO.getDueDate());
        borrowNote = borrowNoteRepository.save(borrowNote);
        return borrowNoteBookMapper.toDto(borrowNote);
    }

    @Override
    public void deleteOrderByID(Long orderID) {
        BorrowNote borrowNote = borrowNoteRepository.findById(orderID).orElseThrow(LibraryException::OrderNotFound);
        borrowNoteRepository.delete(borrowNote);

    }

    @Override
    public BorrowNoteDTO getOrderById(Long orderID) {
        return borrowNoteBookMapper.toDto(borrowNoteRepository.findById(orderID).orElseThrow(LibraryException::OrderNotFound));
    }
    private void orderException (BorrowNoteDTO borrowNoteDTO){
        if (borrowNoteDTO.getBorrowDate().isBefore(LocalDate.now())){
            throw LibraryException.badRequest("WrongTime","Ordering Date Must Be After Now");
        }
        if (borrowNoteDTO.getAddress().isBlank()){
            throw LibraryException.badRequest("WrongAddressFormat","Address Cannot Be Empty");
        }
        if (borrowNoteDTO.getStatusDeliver().equals("")){
            throw LibraryException.badRequest("WrongStatusFormat","Status Cannot Be Empty");
        }

    }
    private void customerException (CustomerDTO customerDTO){
        if (customerDTO.getCustomerLastName().isBlank() || customerDTO.getCustomerFirstName().isBlank()
        || !isAlpha(customerDTO.getCustomerFirstName()) || !isAlpha(customerDTO.getCustomerLastName())){
            throw LibraryException.badRequest("WrongNameFormat","Name Cannot Be Empty And Should Contains Only Letters");
        }
    }
}
