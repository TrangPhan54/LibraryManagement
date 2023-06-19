package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
}
