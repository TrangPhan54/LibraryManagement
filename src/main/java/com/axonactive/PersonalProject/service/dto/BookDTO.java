package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    @JsonIgnore
    private Long id;
    private String name;

    private String bookImage;
    private String contentSummary;
    private LocalDate datePublish;

    private Long publishingHouseID;
    private Long authorID;




}
