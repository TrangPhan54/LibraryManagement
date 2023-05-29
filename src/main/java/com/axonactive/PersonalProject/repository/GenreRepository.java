package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository <Genre,Long> {
    @Query("select g from Genre g where g.name <> ?1")
    List<Genre> getGenreNotLike (String name);
}
