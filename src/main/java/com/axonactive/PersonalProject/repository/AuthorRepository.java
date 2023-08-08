package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository <Author,Long> {
    Optional<Author> findAuthorByFirstName (String firstName);

    Optional<Author> findAuthorByLastName (String lastName);

}
