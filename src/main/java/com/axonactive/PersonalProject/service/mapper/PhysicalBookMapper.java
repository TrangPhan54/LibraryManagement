package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.entity.PhysicalBook;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDTO;
import com.axonactive.PersonalProject.service.dto.PhysicalBookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhysicalBookMapper {
    @Mapping(target = "bookName",source ="physicalBook.book.name")
    @Mapping(target = "datePublish",source ="physicalBook.book.datePublish")
    @Mapping(target = "publishingHouseID",source ="physicalBook.publishingHouse.id")


    PhysicalBookDTO toDto (PhysicalBook physicalBook);
    List<PhysicalBookDTO> toDtos (List <PhysicalBook> physicalBooks);
}
