package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.entity.Response;
import com.axonactive.PersonalProject.exception.LibraryException;
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

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;

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
        Customer customer = customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound);
        Book book = bookRepository.findById(bookID).orElseThrow();
        response.setBook(book);
        response.setCustomer(customer);
        response.setResponseContent(responseDTO.getResponseContent());
        response = responseRepository.save(response);
        return responseMapper.toDto(response);

    }

    @Override
    public ResponseDTO updateResponse(Long responseID, ResponseDTO responseDTO) {
        Response response = responseRepository.findById(responseID).orElseThrow(LibraryException::ResponseNotFound);
        response.setCustomer(customerRepository.findById(responseDTO.getCustomerID()).orElseThrow());
        response.setBook(bookRepository.findById(responseDTO.getBookID()).orElseThrow());
        response.setResponseContent(responseDTO.getResponseContent());

        return responseMapper.toDto(response);
    }

    @Override
    public void deleteResponseByID(Long responseID) {
        Response response = responseRepository.findById(responseID).orElseThrow(LibraryException::ResponseNotFound);
        responseRepository.delete(response);

    }
    private void responseException (ResponseDTO responseDTO){
        if (responseDTO.getResponseContent().isBlank()){
            throw LibraryException.badRequest("WrongResponseFormat"," Response Cannot Be Empty");
        }
    }
    private void customerException (CustomerDTO customerDTO){
        if (customerDTO.getCustomerFirstName().isBlank() || customerDTO.getCustomerLastName().isBlank()
        || !isAlpha(customerDTO.getCustomerFirstName()) || !isAlpha(customerDTO.getCustomerLastName())){
            throw LibraryException.badRequest("WrongNameFormat","Name Cannot Be Empty And Should Contain Only Letters");
        }
    }

}
