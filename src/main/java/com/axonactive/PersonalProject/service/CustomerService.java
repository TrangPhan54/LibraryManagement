package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer ();
//    CustomerDTO getCustomerById (Long customerID);

    CustomerDTO createCustomer (CustomerDTO customerDTO);

    CustomerDTO updateCustomer (Long customerID, CustomerDTO customerDTO);
    void deleteCustomerByID (Long customerID);

    CustomerDTO getCustomerByID (Long customerID);

    List<CustomerDTO> getCustomerByCustomerFirstName (String customerFirstName);
    List<CustomerDTO> getCustomerByCustomerLastName (String customerLastName);

    List<CustomerDTO> getCustomerByCustomerEmail (String customerEmail);


}
