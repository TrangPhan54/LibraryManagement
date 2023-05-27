package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Long> {
    List<Customer> findCustomerByCustomerFirstName (String customerFirstName);

    List<Customer> findCustomerByCustomerLastName (String customerLastName);

    List<Customer> findCustomerByCustomerEmail (String customerEmail);

}
