package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.PhysicalBook;
import com.axonactive.PersonalProject.entity.ReceiptDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptDTO {
//    @JsonIgnore
    private Long id;
    private Long customerID;
    private List<Long> physicalBookIdList;
    private Double liquidationFee;
}
