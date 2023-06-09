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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "fine_fee")
    private Double fineFee;

    @ManyToOne
    @JoinColumn(name = "borrow_id")
    private BorrowNote borrowNote;
}
