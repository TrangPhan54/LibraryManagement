package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBorrowNoteResponseDTO {
    private Long id;
    private Long customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private String address;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private List<BorrowNoteDetailDTO> borrowNoteDetailDTOList;
}
