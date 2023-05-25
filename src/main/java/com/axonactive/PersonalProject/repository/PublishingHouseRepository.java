package com.axonactive.PersonalProject.repository;

import com.axonactive.PersonalProject.entity.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishingHouseRepository extends JpaRepository <PublishingHouse,Long> {
    @Query(value = "SELECT * FROM publishing_house p where p.publishing_house_name = :name", nativeQuery = true)

    List<PublishingHouse> getPublishingHouseByName (String name);
}
