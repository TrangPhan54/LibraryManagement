package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Author;
import com.axonactive.PersonalProject.service.dto.AuthorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDto (Author author);
    List<AuthorDTO> toDtos (List <Author> authors);
}
