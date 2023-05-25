package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.OrderBook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    @JsonIgnore
    private Long orderDetailID;
    private Long quantityOfBooks;

    private Long bookID;

    private Long orderBookID;
}
