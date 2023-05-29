package com.axonactive.PersonalProject.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowNoteDetailDTO {
    @JsonIgnore
    private Long id;
    private Long bookID;
    private Long borrowNoteID;
}
