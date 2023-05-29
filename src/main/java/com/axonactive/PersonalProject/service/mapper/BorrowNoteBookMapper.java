package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowNoteBookMapper {
    @Mapping(target = "customerID",source ="borrowNote.customer.id")
    BorrowNoteDTO toDto (BorrowNote borrowNote);
    List<BorrowNoteDTO> toDtos (List <BorrowNote> borrowNotes);
}
