package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowNoteDetailRepository extends JpaRepository<BorrowNoteDetail, Long> {
    @Query(value = "select count(bnd.book_id) from borrow_note_detail bnd where bnd.book_id = ?1", nativeQuery = true)
    Long countNumberMaxBorrowBook(Long bookId);

    @Query(value = "select bnd1.book_id " +
            "from borrow_note_detail bnd1 " +
            "group by bnd1.book_id  " +
            "having count (bnd1.book_id) = " +

            "(select max (a.numberOfBook) " +
            "from ( " +
            "select count( bnd.book_id) as numberOfBook " +
            "from borrow_note_detail bnd  " +
            "group by bnd.book_id ) as a)", nativeQuery = true)
    List<Long> maxBorrowBook();

    @Query("SELECT b FROM Book b WHERE b.id IN (?1)")
    List<Book> nameOfMaxBorrowBook(List<Long> bookIds);

    @Query("SELECT b FROM Book b WHERE b.id IN (?1)")
    List<Book> nameOfMinBorrowBook(List<Long> bookIds);

    @Query(value = "select bnd1.book_id " +
            "from borrow_note_detail bnd1 " +
            "group by bnd1.book_id  " +
            "having count (bnd1.book_id) = " +

            "(select min (a.numberOfBook) " +
            "from ( " +
            "select count( bnd.book_id) as numberOfBook " +
            "from borrow_note_detail bnd  " +
            "group by bnd.book_id ) as a)", nativeQuery = true)
    List<Long> minBorrowBook();


    List<BorrowNoteDetail> findByBorrowNoteBorrowDateBetween(LocalDate date1, LocalDate date2);


}
