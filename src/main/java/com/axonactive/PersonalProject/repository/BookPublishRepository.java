package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.BookPublish;
import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.axonactive.PersonalProject.service.dto.BookDTO;
import com.axonactive.PersonalProject.service.dto.BookPublishDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookPublishRepository extends JpaRepository <BookPublish,Long> {


}
