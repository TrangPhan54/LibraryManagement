package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReceiptDTO {
    private Long customerID;
    private List<Long> physicalBookIdList;
}
