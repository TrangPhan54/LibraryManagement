package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.dto.BookDTO;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDto (Book book);
    List<BookDTO> toDtos (List <Book> books);
}
