package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.OrderBook;
import com.axonactive.PersonalProject.entity.OrderDetail;
import com.axonactive.PersonalProject.service.dto.OrderBookDTO;
import com.axonactive.PersonalProject.service.dto.OrderDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderBookDetailMapper {

    OrderDetailDTO toDto (OrderDetail orderDetail);
    List<OrderDetailDTO> toDtos (List <OrderDetail> orderDetails);
}

