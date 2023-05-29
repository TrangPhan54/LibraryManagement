package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.GenreBook;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreBookMapper {
    @Mapping(target= "genreID", source = "genreBook.genre.id")
    @Mapping(target= "bookID", source = "genreBook.book.id")

    GenreBookDTO toDto (GenreBook genreBook);
    List<GenreBookDTO> toDtos (List <GenreBook> genreBooks);
}
