package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowNoteDetailDTO {
    @JsonIgnore
    private Long id;
    private Long physicalBookID;
    private String physicalBookName;

    private Long borrowNoteID;
    private Long customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private LocalDate returnDate;
    private Double fineFee;

}
