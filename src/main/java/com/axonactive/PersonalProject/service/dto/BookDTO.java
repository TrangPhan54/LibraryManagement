package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Author;
import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    @JsonIgnore
    private Long bookID;
    private String bookName;

    private String bookImage;
    private String contentSummary;
    private LocalDate datePublish;
    private Double pricePerBook;

    private Long publishingHouseID;
    private Long authorID;

}
