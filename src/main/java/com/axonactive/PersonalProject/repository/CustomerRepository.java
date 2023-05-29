package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Long> {
    List<Customer> findCustomerByFirstName (String firstName);

    List<Customer> findCustomerByFirstNameContaining (String partOfName);

    List<Customer> findCustomerByLastName (String lastName);
    List<Customer> findCustomerByLastNameContaining (String partOfName);
    List<Customer> findCustomerByEmail(String email);

}
