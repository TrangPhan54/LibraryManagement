package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Book;
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
public class PhysicalBookDTO {
    private Long id;

    private Double importPrice;

    private LocalDate importDate;

    private Long publishingHouseID;

    private String bookName;
    private LocalDate datePublish;
}
