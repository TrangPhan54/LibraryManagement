package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    List<CustomerDTO> toDtos(List<Customer> customers);
}
