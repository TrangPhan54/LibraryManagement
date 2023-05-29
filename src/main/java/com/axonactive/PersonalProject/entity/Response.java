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
    @Column (name = "id")
    private Long id;
    @Column (name = "response_content", length = 5000)
    private String responseContent;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;


}
