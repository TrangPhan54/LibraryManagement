package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowNoteDetailRepository extends JpaRepository <BorrowNoteDetail,Long> {
}
