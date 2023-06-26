package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBorrowNoteDetailResponseDTO {
    private Long id;
    private Long physicalBookID;
    private String physicalBookName;
    private Long customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private LocalDate returnDate;
    private Double fineFee;
}
