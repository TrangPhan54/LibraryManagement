package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalBookDTO {
    private Long id;

    private Double importPrice;

    private LocalDate importDate;

    private Long publishingHouseID;

    private String bookName;
    private LocalDate datePublish;
}
