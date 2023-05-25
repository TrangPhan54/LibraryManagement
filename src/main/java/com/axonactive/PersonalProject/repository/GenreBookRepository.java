package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreBookRepository extends JpaRepository <GenreBook,Long> {
}
