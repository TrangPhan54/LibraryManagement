package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "borrow_note")
public class BorrowNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "borrow_id")
    private Long id;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;
    @Column (name = "address")
    private String address;
    @Column (name = "borrow_date")
    private LocalDate borrowDate;
    @Column (name = "return_date")
    private LocalDate returnDate;
    @Column (name = "due_date")
    private LocalDate dueDate;







}
