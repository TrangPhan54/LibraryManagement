package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();
//    CustomerDTO getCustomerById (Long customerID);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long customerID, CustomerDTO customerDTO);

    void deleteCustomerByID(Long customerID);

    CustomerDTO getCustomerByID(Long customerID);

    List<CustomerDTO> getCustomerByFirstName(String firstName);

    List<CustomerDTO> getCustomerByLastName(String lastName);

    List<CustomerDTO> getCustomerByEmail(String email);

    List<CustomerDTO> getCustomerByFirstNameContaining(String partOfName);

    List<CustomerDTO> getCustomerByLastNameContaining (String partOfName);



}
