package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailID;
    @Column(name = "quantityOfBooks")
    private Long quantityOfBooks;

//    @Column(name = "priceOfAllBooks")
//    private Double priceOfAllBooks = this.book.getPricePerBook() * quantityOfBooks;
    @ManyToOne
    @JoinColumn(name = "bookID")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "orderID")
    private OrderBook orderBook;
}
