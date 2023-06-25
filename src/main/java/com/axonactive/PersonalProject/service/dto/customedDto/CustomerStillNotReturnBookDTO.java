package com.axonactive.PersonalProject.service.dto.customedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStillNotReturnBookDTO {
    private Long customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private List<String> physicalBookName;

}
