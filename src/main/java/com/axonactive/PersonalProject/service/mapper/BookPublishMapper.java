package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BookPublish;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.BookPublishDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookPublishMapper {

    BookPublishDTO toDto (BookPublish bookPublish);
    List<BookPublishDTO> toDtos (List <BookPublish> bookPublishes);
}
