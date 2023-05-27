package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Status;
import com.axonactive.PersonalProject.service.dto.BookContentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByBookNameContainingIgnoreCase(String keyword);

    List<Book> findByStatus(Status status);

    @Query("Select b from Book b where b.publishingHouse.publishingHouseName = ?1")
    List<Book> findBookByPublishingHouseName(String publishingHouseName);

    @Query("Select b from Book b where b.author.authorFirstName = ?1")
    List<Book> findBookByAuthorFirstName(String authorFirstName);

    @Query("Select b from Book b where b.author.authorLastName = ?1")
    List<Book> findBookByAuthorLastName(String authorLastName);

    @Query ("select new com.axonactive.PersonalProject.service.dto.BookContentDTO (b.contentSummary)"+
            "from Book b where b.bookName = ?1")
    BookContentDTO findContentSummaryByBookName (String bookName);


}
