package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.OrderBook;
import com.axonactive.PersonalProject.service.dto.OrderBookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderBookMapper {
    @Mapping(target = "customerID",source ="orderBook.customer.customerID")

    OrderBookDTO toDto (OrderBook orderBook);
    List<OrderBookDTO> toDtos (List <OrderBook> orderBooks);
}
