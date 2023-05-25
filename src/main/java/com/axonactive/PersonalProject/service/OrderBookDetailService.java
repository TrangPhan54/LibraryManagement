package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.OrderDetail;
import com.axonactive.PersonalProject.service.dto.OrderBookDTO;
import com.axonactive.PersonalProject.service.dto.OrderDetailDTO;

import java.util.List;

public interface OrderBookDetailService {
    List<OrderDetailDTO> getAllOrderDetail ();
    OrderDetailDTO createOrderDetail (OrderDetailDTO orderDetailDTO, Long bookID, Long orderID);
//    OrderDetailDTO updateOrderDetail (Long orderDetailID, OrderDetailDTO orderBookDTO);
    void deleteOrderDetailByID (Long orderDetailID);
}
