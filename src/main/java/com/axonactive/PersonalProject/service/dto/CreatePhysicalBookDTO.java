package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePhysicalBookDTO {
    private Double importPrice;
    private LocalDate importDate;
    private Long publishingHouseId;
    private Long bookId;
}
