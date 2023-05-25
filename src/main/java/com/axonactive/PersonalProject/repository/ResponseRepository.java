package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository <Response,Long> {
}
