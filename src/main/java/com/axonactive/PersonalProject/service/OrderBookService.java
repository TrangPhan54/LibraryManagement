package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.OrderBookDTO;

import java.util.List;

public interface OrderBookService {
    List<OrderBookDTO> getAllOrder ();
    OrderBookDTO createOrder (OrderBookDTO orderBookDTO, Long customerID);
    OrderBookDTO updateOrder (Long orderID, OrderBookDTO orderBookDTO);
    void deleteOrderByID (Long orderID);
    OrderBookDTO getOrderById (Long orderID);
}
