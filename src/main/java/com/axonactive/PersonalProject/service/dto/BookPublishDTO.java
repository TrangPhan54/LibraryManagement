package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookPublishDTO {
    @JsonIgnore
    private Long bookPublishID;

    private Long bookID;

    private Long publishingHouseID;
}
