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
    @Column (name = "bookID")
    private Long bookID;

    @Column (name = "bookName")
    private String bookName;

    @Column (name = "bookImage", length = 1000)
    private String bookImage;
    @Column (name = "contentSummary", length = 1000)
    private String contentSummary;
    @Column (name = "datePublish")
    private LocalDate datePublish;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn (name = "publishingHouseID")
    private PublishingHouse publishingHouse;

    @ManyToOne
    @JoinColumn (name = "authorID")
    private Author author;
}
