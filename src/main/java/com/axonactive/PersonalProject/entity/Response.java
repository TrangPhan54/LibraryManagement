package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "responseID")
    private Long responseID;
    @Column (name = "responseContent", length = 5000)
    private String responseContent;
    @ManyToOne
    @JoinColumn (name = "customerID")
    private Customer customer;
    @ManyToOne
    @JoinColumn (name = "bookID")
    private Book book;


}
