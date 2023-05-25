package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreBookDTO {
    @JsonIgnore
    private Long genreBookID;
    private Long bookID;

    private Long genreID;
}
