package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowNoteRepository extends JpaRepository<BorrowNote, Long> {
    @Query("Select bn from BorrowNote bn where bn.borrowDate = ?1")
    List<BorrowNote> findBorrowNoteHistoryByBorrowDate(LocalDate borrowDate);



//    List<BorrowNote> nameOfCustomerReturnBookLate();
}
