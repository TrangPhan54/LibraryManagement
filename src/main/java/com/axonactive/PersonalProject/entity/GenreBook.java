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
    @Column (name ="id")
    private Long id;
    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn (name = "genre_id")
    private Genre genre;
}
