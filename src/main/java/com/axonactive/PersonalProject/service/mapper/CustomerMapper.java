package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
//    @Mapping(target= "customerID", source = "customer.id")
//    @Mapping(target= "customerFirstName", source = "customer.firstName")
//    @Mapping(target= "customerLastName", source = "customer.lastName")
//    @Mapping(target= "customerAddress", source = "customer.address")
//    @Mapping(target= "customerPhoneNumber", source = "customer.phoneNumber")



    CustomerDTO toDto (Customer customer);
    List<CustomerDTO> toDtos (List <Customer> customers);
}
