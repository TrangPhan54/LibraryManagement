package com.axonactive.PersonalProject.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @JsonIgnore
    private Long customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerPhoneNumber;


}
