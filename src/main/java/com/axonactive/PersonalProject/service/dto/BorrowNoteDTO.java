package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowNoteDTO {
    @JsonIgnore
    private Long borrowID;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private String address;
    private Status statusDeliver;

    private Long customerID;
}
