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
@Table(name = "borrow_note_detail")
public class BorrowNoteDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "physical_book_id")
    private PhysicalBook physicalBook;
    @Column(name = "fine_fee")
    private Double fineFee;
    @Column (name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "borrow_id")
    private BorrowNote borrowNote;
    @Enumerated(EnumType.STRING)
    private Condition condition;
}
