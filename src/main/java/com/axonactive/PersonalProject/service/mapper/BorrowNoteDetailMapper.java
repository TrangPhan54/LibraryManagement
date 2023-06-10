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
    BorrowNoteDetailDTO toDto (BorrowNoteDetail borrowNoteDetail);
    List<BorrowNoteDetailDTO> toDtos (List <BorrowNoteDetail> borrowNoteDetails);
}

