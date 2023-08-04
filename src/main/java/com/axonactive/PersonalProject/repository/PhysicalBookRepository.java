package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.PhysicalBook;
import com.axonactive.PersonalProject.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface PhysicalBookRepository extends JpaRepository<PhysicalBook, Long> {

    @Query(value = "select count(pb.book_id) from physical_book pb join book b on b.name like ?1 and pb.book_id = b.id ", nativeQuery = true)
    Long countBookBaseOnBookName(String bookName);

    List<PhysicalBook> findByStatus(Status status);


}
