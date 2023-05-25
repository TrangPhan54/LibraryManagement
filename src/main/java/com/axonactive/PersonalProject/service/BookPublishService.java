package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.BookPublishDTO;

import java.util.List;

public interface BookPublishService {
    List<BookPublishDTO> getAllBookPublish ();

    BookPublishDTO createBookPublish (BookPublishDTO bookPublishDTO, Long bookID, Long publishingHouseID);

    BookPublishDTO updateBookPublish (Long bookPublishID, BookPublishDTO bookPublishDTO);
    void deleteBookPublishById (Long bookPublishID);

}
