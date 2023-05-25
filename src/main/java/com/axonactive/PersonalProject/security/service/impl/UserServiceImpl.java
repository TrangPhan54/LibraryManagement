package com.axonactive.PersonalProject.security.service.impl;

import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.security.service.CustomerSecurityService;
import com.axonactive.PersonalProject.security.service.dto.CustomerSecurityDTO;
import com.axonactive.PersonalProject.security.service.mapper.CustomerSecurityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements CustomerSecurityService {
    private final CustomerRepository customerRepository;
    private final CustomerSecurityMapper customerSecurityMapper;

    @Override
    public List<CustomerSecurityDTO> getSecurityCustomers() {
        return customerSecurityMapper.mapToSecurityDtos(customerRepository.findAll());
    }
}
