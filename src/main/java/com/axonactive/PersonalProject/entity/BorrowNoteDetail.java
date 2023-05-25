package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrow_note_detail")
public class BorrowNoteDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowDetailID;

    @ManyToOne
    @JoinColumn(name = "bookID")
    private Book book;
    @Column(name = "quantityOfBooks")
    private Long quantityOfBooks;

    @ManyToOne
    @JoinColumn(name = "borrowID")
    private BorrowNote borrowNote;
}
