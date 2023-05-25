package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.BookPublish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPublishRepository extends JpaRepository <BookPublish,Long> {
}
