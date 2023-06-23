package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.entity.Status;
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
public class BorrowNoteDTO {
//    @JsonIgnore
    private Long id;
    private Long customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private String address;
    private LocalDate borrowDate;
    private LocalDate dueDate;



}
