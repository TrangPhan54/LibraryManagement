package com.axonactive.PersonalProject.security.repository;

import com.axonactive.PersonalProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerSecurityRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail (String email);
}
