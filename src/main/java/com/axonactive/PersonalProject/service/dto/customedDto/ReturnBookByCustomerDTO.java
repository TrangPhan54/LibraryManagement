package com.axonactive.PersonalProject.service.dto.customedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBookByCustomerDTO {
    private Long customerId;
    private List<Long> physicalBookIds;

}
