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

    @Query(value = "select a.customer_id \n" +
            "from borrow_note a\n" +
            "group by a.customer_id \n" +
            "having count (a.customer_id) = ( \n" +
            "\t\t\t\t\t\t\t\tselect max (bn.customer_id)\n" +
            "\t\t\t\t\t\t\t\tfrom borrow_note bn \n" +
            "\t\t\t\t\t\t\t\twhere bn.customer_id in \n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t(select count(bn2.customer_id)\n" +
            " \t\t\t\t\t\t\t\t\t\t\t\t\t\tfrom borrow_note bn2 \n" +
            " \t\t\t\t\t\t\t\t\t\t\t\t\t\tgroup by bn2.customer_id ))", nativeQuery = true)
    List<Long> maxBorrowCustomer();

    @Query("SELECT c FROM Customer c WHERE c.id IN (?1)")
    List<Customer> nameOfMaxBorrowCustomer(List<Long> customerIds);

    @Query(value = "select a.customer_id \n" +
            "from borrow_note a\n" +
            "group by a.customer_id \n" +
            "having count (a.customer_id) = ( \n" +
            "\t\t\t\t\t\t\t\tselect min (bn.customer_id)\n" +
            "\t\t\t\t\t\t\t\tfrom borrow_note bn \n" +
            "\t\t\t\t\t\t\t\twhere bn.customer_id in \n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t(select count(bn2.customer_id)\n" +
            " \t\t\t\t\t\t\t\t\t\t\t\t\t\tfrom borrow_note bn2 \n" +
            " \t\t\t\t\t\t\t\t\t\t\t\t\t\tgroup by bn2.customer_id ))", nativeQuery = true)
    List<Long> minBorrowCustomer();

    @Query("SELECT c FROM Customer c WHERE c.id IN (?1)")
    List<Customer> nameOfMinBorrowCustomer(List<Long> customerIds);


//    List<BorrowNote> nameOfCustomerReturnBookLate();
}
