package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBorrowNoteDTO {
    private Long customerID;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    private List<Long> physicalBookIdList;
}
