package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.dto.BookDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target= "authorID", source = "book.author.id")
    BookDTO toDto (Book book);
    List<BookDTO> toDtos (List <Book> books);
}
