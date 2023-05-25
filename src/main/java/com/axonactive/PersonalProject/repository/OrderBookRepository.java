package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends JpaRepository <OrderBook,Long> {
}
