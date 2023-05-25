package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowNoteDetailMapper {
    @Mapping(target = "bookID", source = "borrowNoteDetail.book.bookID")
    @Mapping(target = "borrowNoteID", source ="borrowNoteDetail.borrowNote.borrowID")
    BorrowNoteDetailDTO toDto (BorrowNoteDetail borrowNoteDetail);
    List<BorrowNoteDetailDTO> toDtos (List <BorrowNoteDetail> borrowNoteDetails);
}

