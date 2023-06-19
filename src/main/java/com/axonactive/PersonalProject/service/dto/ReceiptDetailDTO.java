package com.axonactive.PersonalProject.service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptDetailDTO {
    private Long id;
    private Long receiptID;
    private Long physicalBookID;
    private Double liquidationFee;

}
