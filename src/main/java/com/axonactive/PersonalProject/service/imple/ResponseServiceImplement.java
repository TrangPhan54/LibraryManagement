package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.entity.Response;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.repository.ResponseRepository;
import com.axonactive.PersonalProject.service.ResponseService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.ResponseDTO;
import com.axonactive.PersonalProject.service.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResponseServiceImplement implements ResponseService {
    private final ResponseRepository responseRepository;

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final ResponseMapper responseMapper;

    @Override
    public List<ResponseDTO> getAllResponse() {
        List<Response> responses = responseRepository.findAll();
        return responseMapper.toDtos(responses);
    }

    @Override
    public ResponseDTO createResponse(Long customerID, Long bookID, ResponseDTO responseDTO) {
        Response response = new Response();
        Customer customer = customerRepository.findById(customerID).orElseThrow(BookStoreException::CustomerNotFound);
        Book book = bookRepository.findById(bookID).orElseThrow();
        response.setBook(book);
        response.setCustomer(customer);
        response = responseRepository.save(response);
        return responseMapper.toDto(response);

    }

    @Override
    public ResponseDTO updateResponse(Long responseID, ResponseDTO responseDTO) {
        Response response = responseRepository.findById(responseID).orElseThrow(BookStoreException::ResponseNotFound);
        response.setCustomer(customerRepository.findById(responseDTO.getCustomerID()).orElseThrow());
        response.setBook(bookRepository.findById(responseDTO.getBookID()).orElseThrow());

        return responseMapper.toDto(response);
    }

    @Override
    public void deleteResponseByID(Long responseID) {
        responseRepository.deleteById(responseID);

    }
}
