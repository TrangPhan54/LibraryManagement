package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAnalyticDTO {
    private String name;
    private Long numberOfPhysicalCopies;
}
