package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.service.CustomerService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;
import static com.axonactive.PersonalProject.exception.BooleanMethod.isNumberOnly;


@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toDtos(customers);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerID) {
        Customer cus =  customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound);
        return customerMapper.toDto(cus);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .customerFirstName(customerDTO.getCustomerFirstName())
                .customerLastName(customerDTO.getCustomerLastName())
                .customerAddress(customerDTO.getCustomerAddress())
                .customerPhoneNumber(customerDTO.getCustomerPhoneNumber())
                .build();

        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long customerID, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound);
        customer.setCustomerFirstName(customerDTO.getCustomerFirstName());
        customer.setCustomerLastName(customerDTO.getCustomerLastName());
        customer.setCustomerPhoneNumber(customerDTO.getCustomerPhoneNumber());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);




    }

    @Override
    public void deleteCustomerByID(Long customerID) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound);
        customerRepository.delete(customer);

    }

    @Override
    public CustomerDTO getCustomerByID(Long customerID) {
        return customerMapper.toDto(customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound));
    }
    private void customerException (CustomerDTO customerDTO){
        if(!isAlpha(customerDTO.getCustomerFirstName()) || !isAlpha(customerDTO.getCustomerLastName()) ||
                customerDTO.getCustomerFirstName().isBlank() || customerDTO.getCustomerLastName().isBlank()){
            throw LibraryException.badRequest("WrongNameFormat","Name Of Customer Should Contain Only Letters And Must Not Be Empty");
        }
        if (!isNumberOnly(customerDTO.getCustomerPhoneNumber())){
            throw LibraryException.badRequest("WrongNumberFormat","Phone Number Should Contains Only Numbers");
        }
        if (customerDTO.getCustomerAddress().isBlank()){
            throw LibraryException.badRequest("WrongAddressFormat","Address Cannot Be Empty");
        }
    }


}
