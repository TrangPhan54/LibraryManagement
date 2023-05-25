package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    @JsonIgnore
    private Long responseID;
    private String responseContent;

    private Long customerID;

    private Long bookID;
}
