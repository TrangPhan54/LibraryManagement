package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowNoteDetailRepository extends JpaRepository <BorrowNoteDetail,Long> {
    @Query(value = "select count(bnd.book_id) from borrow_note_detail bnd where bnd.book_id = ?1",nativeQuery = true)
    Long countNumberMaxBorrowBook (Long bookId);

    @Query (value = "select a.book_id\n" +
            "from borrow_note_detail a\n" +
            "group by book_id\n" +
            "having count(a.book_id) = (\n" +

            "\t\t\t\t\t\t\tselect max (bnd1.book_id)\n" +
            "\t\t\t\t\t\t\tfrom public.borrow_note_detail bnd1\n" +
            "\t\t\t\t\t\t\twhere bnd1.book_id in\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t(select count (bnd.book_id)\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\tfrom public.borrow_note_detail bnd\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\tgroup by bnd.book_id))\n",nativeQuery = true)

    List<Long> maxBorrowBook ();

    @Query("SELECT b FROM Book b WHERE b.id IN (?1)")
    List<Book> nameOfMaxBorrowBook(List<Long> bookIds);
    @Query("SELECT b FROM Book b WHERE b.id IN (?1)")
    List<Book> nameOfMinBorrowBook(List<Long> bookIds);

    @Query (value = "select a.book_id\n" +
            "from borrow_note_detail a\n" +
            "group by book_id\n" +
            "having count(a.book_id) = (\n" +

            "\t\t\t\t\t\t\tselect min (bnd1.book_id)\n" +
            "\t\t\t\t\t\t\tfrom public.borrow_note_detail bnd1\n" +
            "\t\t\t\t\t\t\twhere bnd1.book_id in\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t(select count (bnd.book_id)\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\tfrom public.borrow_note_detail bnd\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\tgroup by bnd.book_id))\n",nativeQuery = true)

    List<Long> minBorrowBook ();








}
