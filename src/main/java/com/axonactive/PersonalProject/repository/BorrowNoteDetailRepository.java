package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowNoteDetailRepository extends JpaRepository<BorrowNoteDetail, Long> {
    List<BorrowNoteDetail> findByBorrowNoteCustomerId(Long customerId);

    List<BorrowNoteDetail> findByBorrowNoteBorrowDateBetween(LocalDate date1, LocalDate date2);

    @Query("SELECT bnd FROM BorrowNoteDetail bnd WHERE bnd.borrowNote.id = ?1")
    List<BorrowNoteDetail> findByBorrowNoteId(Long id);
    @Query("SELECT bnd FROM BorrowNoteDetail bnd WHERE bnd.physicalBook.id = ?1")
    List<BorrowNoteDetail> findByPhysicalBookId(Long id);

//    List<BorrowNoteDetail> findByBorrowNoteBorrowDateBetween ();


}
