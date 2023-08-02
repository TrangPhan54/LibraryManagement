package com.axonactive.PersonalProject.service.dto.customedDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReturnPhysicalBookDTO {
    private Long physicalBookId;
    private Long borrowNoteId;
    private String bookName;
    private String bookImage;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean late;
}