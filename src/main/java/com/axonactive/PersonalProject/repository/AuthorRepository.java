package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository <Author,Long> {
    Author findAuthorByFirstName (String firstName);
}
