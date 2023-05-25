package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.entity.Status;
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
public class OrderBookDTO {
    @JsonIgnore
    private Long orderBookID;
    private LocalDate orderingDate;
    private String address;
    private Status statusDeliver;

    private Long customerID;
}
