package com.axonactive.PersonalProject.service.mapper;

import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PublishingHouseMapper {

    PublishingHouseDTO toDto(PublishingHouse publishingHouse);

    List<PublishingHouseDTO> toDtos(List<PublishingHouse> publishingHouses);
}


