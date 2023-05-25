package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.BorrowNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowNoteRepository extends JpaRepository <BorrowNote,Long> {
}
