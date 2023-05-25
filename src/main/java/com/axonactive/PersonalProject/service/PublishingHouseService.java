package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;

import java.util.List;

public interface PublishingHouseService {
    List<PublishingHouseDTO> getAllPublishingHouse ();
    PublishingHouseDTO createPublishingHouse (PublishingHouseDTO publishingHouseDTO);
    PublishingHouseDTO updatePublishingHouse (Long publishingHouseID, PublishingHouseDTO publishingHouseDTO);
    void deletePublishingHouseByID (Long publishingHouseID);


    List<PublishingHouseDTO> getPublishingHouseByName (String name);


    PublishingHouseDTO getPublishingHouseById (Long publishingHouseID);
}
