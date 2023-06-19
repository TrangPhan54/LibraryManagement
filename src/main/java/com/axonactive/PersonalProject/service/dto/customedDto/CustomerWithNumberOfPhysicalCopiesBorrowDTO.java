package com.axonactive.PersonalProject.service.dto.customedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithNumberOfPhysicalCopiesBorrowDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private Long numberOfPhysicalCopiesBorrow;
}
