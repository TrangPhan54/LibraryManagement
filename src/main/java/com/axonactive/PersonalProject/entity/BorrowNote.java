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
@Table (name = "borrow_note")
public class BorrowNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "borrow_id")
    private Long borrowID;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;
    @Column (name = "address")
    private String address;
    @Column (name = "borrow_date")
    private LocalDate borrowDate;
    @Column (name = "due_date")
    private LocalDate dueDate;





}
