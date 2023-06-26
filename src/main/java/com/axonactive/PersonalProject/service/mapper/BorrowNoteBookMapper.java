package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.CreateBorrowNoteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowNoteBookMapper {
    @Mapping(target = "customerID",source ="borrowNote.customer.id")
    @Mapping(target = "customerFirstName",source ="borrowNote.customer.firstName")
    @Mapping(target = "customerLastName",source ="borrowNote.customer.lastName")
    @Mapping(target = "customerPhoneNumber",source ="borrowNote.customer.phoneNumber")
    BorrowNoteDTO toDto (BorrowNote borrowNote);

    @Mapping(target = "customerID",source ="borrowNote.customer.id")
    @Mapping(target = "customerFirstName",source ="borrowNote.customer.firstName")
    @Mapping(target = "customerLastName",source ="borrowNote.customer.lastName")
    @Mapping(target = "customerPhoneNumber",source ="borrowNote.customer.phoneNumber")
    CreateBorrowNoteResponseDTO toResponseDto (BorrowNote borrowNote);
    List<BorrowNoteDTO> toDtos (List <BorrowNote> borrowNotes);
}
