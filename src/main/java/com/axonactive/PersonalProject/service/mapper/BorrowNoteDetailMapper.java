package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowNoteDetailMapper {
    @Mapping(target = "physicalBookID", source = "borrowNoteDetail.physicalBook.id")
    @Mapping(target = "borrowNoteID", source ="borrowNoteDetail.borrowNote.id")
    @Mapping(target = "physicalBookName", source = "borrowNoteDetail.physicalBook.book.name")
    @Mapping(target = "customerFirstName", source = "borrowNoteDetail.borrowNote.customer.firstName")
    @Mapping(target = "customerLastName", source = "borrowNoteDetail.borrowNote.customer.lastName")
    @Mapping(target = "customerPhoneNumber", source = "borrowNoteDetail.borrowNote.customer.phoneNumber")
    @Mapping(target = "customerID", source = "borrowNoteDetail.borrowNote.customer.id")

    BorrowNoteDetailDTO toDto (BorrowNoteDetail borrowNoteDetail);
    List<BorrowNoteDetailDTO> toDtos (List <BorrowNoteDetail> borrowNoteDetails);
}

