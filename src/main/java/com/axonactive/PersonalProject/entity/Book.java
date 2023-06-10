package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name ="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "image", length = 1000)
    private String bookImage;
    @Column (name = "content_summary", length = 1000)
    private String contentSummary;
    @Column (name = "date_publish")
    private LocalDate datePublish;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn (name = "author_id")
    private Author author;
}
