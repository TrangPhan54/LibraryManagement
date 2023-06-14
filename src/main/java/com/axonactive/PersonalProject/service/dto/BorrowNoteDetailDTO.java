package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowNoteDetailDTO {
    @JsonIgnore
    private Long id;
    private Long physicalBookID;
    private Long borrowNoteID;
    private Double fineFee;

}
