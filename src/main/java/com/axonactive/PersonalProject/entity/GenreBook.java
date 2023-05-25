package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "genreBook")
public class GenreBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="genreBookID")
    private Long genreBookID;
    @ManyToOne
    @JoinColumn (name = "bookID")
    private Book book;
    @ManyToOne
    @JoinColumn (name = "genreID")
    private Genre genre;

}
