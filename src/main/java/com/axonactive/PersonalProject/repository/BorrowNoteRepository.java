package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowNoteRepository extends JpaRepository<BorrowNote, Long> {
    @Query("Select bn from BorrowNote bn where bn.borrowDate = ?1")
    List<BorrowNote> findBorrowNoteHistoryByBorrowDate(LocalDate borrowDate);

    @Query(value = "select a.customer_id" +
            "from borrow_note a " +
            "group by a.customer_id  " +
            "having count (a.customer_id) = ( " +
            "select max (bn.customer_id) " +
            "from borrow_note bn  " +
            "where bn.customer_id in  " +
            "(select count(bn2.customer_id) " +
            " from borrow_note bn2  " +
            "group by bn2.customer_id ))", nativeQuery = true)
    List<Long> maxBorrowCustomer();

    @Query("SELECT c FROM Customer c WHERE c.id IN (?1)")
    List<Customer> nameOfMaxBorrowCustomer(List<Long> customerIds);

    @Query(value = "select a.customer_id  " +
            "from borrow_note a " +
            "group by a.customer_id  " +
            "having count (a.customer_id) = (  " +
            " select min (bn.customer_id) " +
            " from borrow_note bn  " +
            " where bn.customer_id in  " +
            " (select count(bn2.customer_id) " +
            "  from borrow_note bn2  " +
            "  group by bn2.customer_id ))", nativeQuery = true)
    List<Long> minBorrowCustomer();

    @Query("SELECT c FROM Customer c WHERE c.id IN (?1)")
    List<Customer> nameOfMinBorrowCustomer(List<Long> customerIds);


//    List<BorrowNote> nameOfCustomerReturnBookLate();
}
