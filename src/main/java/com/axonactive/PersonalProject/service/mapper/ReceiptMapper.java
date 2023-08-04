package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Receipt;
import com.axonactive.PersonalProject.service.dto.ReceiptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {
    @Mapping(target = "customerID", source = "receipt.customer.id")
    ReceiptDTO toDto(Receipt receipt);

    List<ReceiptDTO> toDtos(List<Receipt> receipts);


}
