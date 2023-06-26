package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.Status;
import com.axonactive.PersonalProject.service.dto.BookAnalyticDTO;
import com.axonactive.PersonalProject.service.dto.BookContentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByNameContainingIgnoreCase(String keyword);

    List<Book> findByName(String name);

//    List<Book> findByStatus(Status status);

//    @Query("Select b from Book b where b.publishingHouse.name = ?1")
//    List<Book> findBookByPublishingHouseName(String publishingHouseName);

    @Query("Select b from Book b where b.author.firstName = ?1")
    List<Book> findBookByAuthorFirstName(String authorFirstName);

    @Query("Select b from Book b where b.author.lastName = ?1")
    List<Book> findBookByAuthorLastName(String authorLastName);

    @Query("select new com.axonactive.PersonalProject.service.dto.BookContentDTO (b.contentSummary)" +
            "from Book b where b.name = ?1")
    BookContentDTO findContentSummaryByBookName(String bookName);

    @Query("select new com.axonactive.PersonalProject.service.dto.BookContentDTO (b.contentSummary)" +
            "from Book b where b.name like ?1")
    BookContentDTO findContentSummaryByBookNameContaining(String bookName);
    @Query("select b from Book b where b.author.lastName like ?1")
    List<Book> findBookByAuthorLastNameContaining(String partOfName);
    @Query("select b from Book b where b.author.firstName like ?1")
    List<Book> findBookByAuthorFirstNameContaining(String partOfName);
    List<Book> findBookByAuthorLastNameContainingIgnoreCase(String partOfName);

    List<Book> findBookByAuthorFirstNameContainingIgnoreCase(String partOfName);

    List<Book> findAllById(Iterable<Long> bookIds);

    //    @Query (value = "select new com.axonactive.PersonalProject.service.dto.BookAnalyticDTO (b.name), count(b.name) from physical_book pb join book b on pb.book_id = b.id group by b.name ", nativeQuery = true)
    @Query(nativeQuery = true)
    List<BookAnalyticDTO> getBookAnalytic();




}
