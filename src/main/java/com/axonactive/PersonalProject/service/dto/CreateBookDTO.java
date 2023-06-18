package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookDTO {
//    private Long bookId;
    private String name;
    private String bookImage;
    private String contentSummary;
    private LocalDate datePublish;
    private Long authorID;
    private Long publishingHouseId;
    private Long numberOfPhysicalBook;
    private LocalDate importDate;
    private Double importPrice;
}
