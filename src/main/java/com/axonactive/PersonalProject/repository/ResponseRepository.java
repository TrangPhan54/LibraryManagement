package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Response;
import com.axonactive.PersonalProject.service.dto.BookContentDTO;
import com.axonactive.PersonalProject.service.dto.ResponseContentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    @Query(value = "select * from response r join book b on r.book_id = b.id where b.id = ?1 ", nativeQuery = true)
    List<Response> findResponseOfABook(Long bookId);


//    @Query(value = "select new com.axonactive.PersonalProject.service.dto.ResponseContentDTO (r.responseContent) response r join book b " +
//            "on r.book_id = b.id where b.id = ?1 " , nativeQuery = true)
//    List<Response> findResponseOfABookByBookName (String bookName);


    @Query(value = "select * from response r join book b on r.book_id = b.id where b.name like ?1 ", nativeQuery = true)
    List<Response> findResponseOfABookByName(String bookName);

}
