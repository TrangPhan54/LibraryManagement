package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBook ();

    BookDTO createBook (BookDTO bookDTO, Long publishingHouseID, Long authorID);

    BookDTO updateBook (Long bookID, BookDTO bookDTO);
    void deleteBookById (Long bookID);

    BookDTO getBookById (Long bookID);


}
