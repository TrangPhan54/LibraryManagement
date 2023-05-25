package com.axonactive.PersonalProject.security.service.mapper;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.security.service.dto.CustomerSecurityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerSecurityMapper {
    CustomerSecurityDTO mapToSecurityDto (Customer customer);
    List<CustomerSecurityDTO> mapToSecurityDtos (List<Customer> customers);

}
