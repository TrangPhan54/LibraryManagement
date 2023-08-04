package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowNoteDTO {
    private Long id;
    private Long customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private String address;
    private LocalDate borrowDate;
    private LocalDate dueDate;


}
