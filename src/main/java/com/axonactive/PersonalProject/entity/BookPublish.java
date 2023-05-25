package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "bookPublish")
public class BookPublish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookPublishID;
    @ManyToOne
    @JoinColumn (name = "bookId")
    private Book book;
    @ManyToOne
    @JoinColumn (name = "publishingHouseId")
    private PublishingHouse publishingHouse;
}
