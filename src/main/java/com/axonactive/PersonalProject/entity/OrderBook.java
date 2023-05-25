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
@Table (name = "orderBook")
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "order_id")
    private Long orderID;
    @Column (name = "ordering_date")
    private LocalDate orderingDate;
    @Column (name = "address")
    private String address;
    @Column (name = "status_deliver")
    @Enumerated(EnumType.STRING)
    private Status statusDeliver;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;


}
