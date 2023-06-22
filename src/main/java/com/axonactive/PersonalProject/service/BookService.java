package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.service.dto.BookAnalyticDTO;
import com.axonactive.PersonalProject.service.dto.BookContentDTO;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.CreateBookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBook();

    BookDTO createBook(CreateBookDTO createBookDTO);

    BookDTO updateBook(Long bookID, BookDTO bookDTO);

    void deleteBookById(Long bookID);

    BookDTO getBookById(Long bookID);

    // 1.Tim ten sach co chua ki tu gan dung
    List<BookDTO> getByBookNameContainingIgnoreCase(String keyword);

    List<BookDTO> getByName(String name);



    // 4. Tim sach boi ten tac gia
    List<BookDTO> getBookByAuthorFirstName(String authorFirstName);

    // 5. Tim sach boi ho tac gia
    List<BookDTO> getBookByAuthorLastName(String authorLastName);

    // 6. Tim tom tat noi dung sach thong qua ten sach
    BookContentDTO findContentSummaryByBookName(String bookName);
    // 7. Tim tom tat noi dung sach thong qua ten sach co chua ki tu nao do

    BookContentDTO findContentSummaryByBookNameContaining(String bookName);

    List<BookDTO> getBookByAuthorLastNameContaining(String partOfName);

    List<BookDTO> getBookByAuthorFirstNameContaining(String partOfName);

    List<BookDTO> getBookByAuthorLastNameContainingIgnoreCase(String partOfName);

    List<BookDTO> getBookByAuthorFirstNameContainingIgnoreCase(String partOfName);

    List<BookDTO> findAllById(Iterable<Long> bookIds);

    List<BookAnalyticDTO> getBookAnalytic();

    List<BookDTO> findBookByNameContaining(String name);
    List<BookDTO> getBookPublishBefore2000 ();

    List<BookDTO> getBookByAuthorFirstName1 (String firstName);

    List<BookDTO> getBookByGenreName (String genreName);



//
//    List<Book> getBestBorrowBooksBetweenDate (LocalDate date1, LocalDate date2);
//
//


}
