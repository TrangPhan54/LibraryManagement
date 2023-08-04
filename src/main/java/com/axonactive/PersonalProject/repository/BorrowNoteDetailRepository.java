package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowNoteDetailRepository extends JpaRepository<BorrowNoteDetail, Long> {
    List<BorrowNoteDetail> findByBorrowNoteCustomerId(Long customerId);

    List<BorrowNoteDetail> findByBorrowNoteBorrowDateBetween(LocalDate date1, LocalDate date2);

}
