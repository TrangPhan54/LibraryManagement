package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Genre;

import com.axonactive.PersonalProject.service.dto.GenreDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDto (Genre genre);
    List<GenreDTO> toDtos (List <Genre> genres);
}
