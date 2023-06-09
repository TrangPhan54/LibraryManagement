package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.GenreBook;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreBookRepository extends JpaRepository<GenreBook, Long> {
    // 1.Tim sach boi ten the loai
    List<GenreBook> findByGenreNameContaining (String name);
    // 2. Tim sach boi ten the loai va ten sach co chua ki tu nao do
    List<GenreBook> findByGenreNameContainingAndBookNameContaining (String genreName, String bookName);
    @Query (value = "select b" +
            "from genre g join genre_book gb  " +
            "on g.id = gb.genre_id  " +
            "join book b " +
            "on b.id = gb.book_id where b.name like ?1 ", nativeQuery = true)
    List<GenreBook> getBookHaveRelation (String bookName);

}
