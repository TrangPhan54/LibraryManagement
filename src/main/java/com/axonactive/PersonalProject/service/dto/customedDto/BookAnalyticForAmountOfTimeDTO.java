package com.axonactive.PersonalProject.service.dto.customedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAnalyticForAmountOfTimeDTO {
    private Long bookID;
    private String bookTitle;
    private Long numberOfPhysicalBookCopies;
}
