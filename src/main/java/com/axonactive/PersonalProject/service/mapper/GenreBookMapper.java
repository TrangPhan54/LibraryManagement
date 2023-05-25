package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.GenreBook;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreBookMapper {

    GenreBookDTO toDto (GenreBook genreBook);
    List<GenreBookDTO> toDtos (List <GenreBook> genreBooks);
}
