package com.axonactive.PersonalProject.security.service.impl;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.security.repository.CustomerSecurityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    private final CustomerSecurityRepository customerSecurityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
        Customer customer = customerSecurityRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + customerEmail));

        return UserDetailsImpl.build(customer);
    }

    @org.springframework.transaction.annotation.Transactional
    public UserDetails validateUser(String customerEmail) {
        Customer customer = customerSecurityRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + customerEmail));

        return UserDetailsImpl.build(customer);
    }

}
