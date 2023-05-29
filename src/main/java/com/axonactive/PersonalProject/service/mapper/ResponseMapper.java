package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Author;
import com.axonactive.PersonalProject.entity.Response;
import com.axonactive.PersonalProject.service.dto.AuthorDTO;
import com.axonactive.PersonalProject.service.dto.ResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    @Mapping(target = "bookID", source = "response.book.id")
    @Mapping(target = "customerID", source = "response.customer.id")
    ResponseDTO toDto(Response response);

    List<ResponseDTO> toDtos(List<Response> responses);
}
