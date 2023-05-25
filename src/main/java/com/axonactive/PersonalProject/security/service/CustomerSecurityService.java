package com.axonactive.PersonalProject.security.service;

import com.axonactive.PersonalProject.security.service.dto.CustomerSecurityDTO;

import java.util.List;

public interface CustomerSecurityService {
    List<CustomerSecurityDTO> getSecurityCustomers ();
}
