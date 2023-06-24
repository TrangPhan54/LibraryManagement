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
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if(!isAlpha(customerDTO.getFirstName()) || !isAlpha(customerDTO.getLastName()) ||
                customerDTO.getFirstName().isBlank() || customerDTO.getLastName().isBlank()){
            throw LibraryException.badRequest("WrongNameFormat","Name Of Customer Should Contain Only Letters And Must Not Be Empty");
        }
        if (!isNumberOnly(customerDTO.getPhoneNumber())){
            throw LibraryException.badRequest("WrongNumberFormat","Phone Number Should Contains Only Numbers");
        }
        if (customerDTO.getAddress().isBlank()){
            throw LibraryException.badRequest("WrongAddressFormat","Address Cannot Be Empty");
        }
        Customer customer = Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .address(customerDTO.getAddress())
                .active(customerDTO.getActive())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();

        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long customerID, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(LibraryException::CustomerNotFound);
        if(!isAlpha(customerDTO.getFirstName()) || !isAlpha(customerDTO.getLastName()) ||
                customerDTO.getFirstName().isBlank() || customerDTO.getLastName().isBlank()){
            throw LibraryException.badRequest("WrongNameFormat","Name Of Customer Should Contain Only Letters And Must Not Be Empty");
        }
        if (!isNumberOnly(customerDTO.getPhoneNumber())){
            throw LibraryException.badRequest("WrongNumberFormat","Phone Number Should Contains Only Numbers");
        }
        if (customerDTO.getAddress().isBlank()){
            throw LibraryException.badRequest("WrongAddressFormat","Address Cannot Be Empty");
        }
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());
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

    @Override
    public List<CustomerDTO> getCustomerByFirstName(String firstName) {
        return customerMapper.toDtos(customerRepository.findCustomerByFirstName(firstName));
    }
    @Override
    public List<CustomerDTO> getCustomerByLastName(String lastName) {
        return customerMapper.toDtos(customerRepository.findCustomerByLastName(lastName));
    }

    @Override
    public List<CustomerDTO> getCustomerByEmail(String email) {
        return customerMapper.toDtos(customerRepository.findCustomerByEmail(email));
    }

    @Override
    public List<CustomerDTO> getCustomerByFirstNameContaining(String partOfName) {
        return customerMapper.toDtos(customerRepository.findCustomerByFirstNameContaining(partOfName));
    }

    @Override
    public List<CustomerDTO> getCustomerByLastNameContaining(String partOfName) {
        return customerMapper.toDtos(customerRepository.findCustomerByLastNameContaining(partOfName));
    }


}
