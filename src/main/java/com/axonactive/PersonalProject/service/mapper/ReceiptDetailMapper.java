package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.entity.ReceiptDetail;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.dto.ReceiptDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ReceiptDetailMapper {
    @Mapping(target = "physicalBookID", source = "receiptDetail.physicalBook.id")
    @Mapping(target = "receiptID", source ="receiptDetail.receipt.id")
    ReceiptDetailDTO toDto (ReceiptDetail receiptDetail);
    List<ReceiptDetailDTO> toDtos (List <ReceiptDetail> receiptDetails);
}
