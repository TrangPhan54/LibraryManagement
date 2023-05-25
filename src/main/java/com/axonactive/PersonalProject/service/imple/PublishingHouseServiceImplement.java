package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.PublishingHouse;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.PublishingHouseRepository;
import com.axonactive.PersonalProject.service.PublishingHouseService;
import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;
import com.axonactive.PersonalProject.service.mapper.PublishingHouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class PublishingHouseServiceImplement implements PublishingHouseService {
    private final PublishingHouseRepository publishingHouseRepository;
    private final PublishingHouseMapper publishingHouseMapper;
    @Override
    public List<PublishingHouseDTO> getAllPublishingHouse() {
        List<PublishingHouse> publishingHouses = publishingHouseRepository.findAll();
        return publishingHouseMapper.toDtos(publishingHouses);
    }

    @Override
    public PublishingHouseDTO createPublishingHouse(PublishingHouseDTO publishingHouseDTO) {
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setPublishingHouseName(publishingHouseDTO.getPublishingHouseName());
        publishingHouse = publishingHouseRepository.save(publishingHouse);
        return publishingHouseMapper.toDto(publishingHouse);
    }

    @Override
    public PublishingHouseDTO updatePublishingHouse(Long publishingHouseID, PublishingHouseDTO publishingHouseDTO) {
        PublishingHouse publishingHouse = publishingHouseRepository.findById(publishingHouseID).orElseThrow(BookStoreException::PublishingHouseNotFound);
        publishingHouse.setPublishingHouseName(publishingHouseDTO.getPublishingHouseName());
        publishingHouse = publishingHouseRepository.save(publishingHouse);
        return publishingHouseMapper.toDto(publishingHouse);
    }

    @Override
    public void deletePublishingHouseByID(Long publishingHouseID) {
        publishingHouseRepository.deleteById(publishingHouseID);

    }

    @Override
    public List<PublishingHouseDTO> getPublishingHouseByName(String name) {
        return publishingHouseMapper.toDtos(publishingHouseRepository.getPublishingHouseByName(name));
    }

    @Override
    public PublishingHouseDTO getPublishingHouseById(Long publishingHouseID) {
        return publishingHouseMapper.toDto(publishingHouseRepository.findById(publishingHouseID).orElseThrow(BookStoreException::PublishingHouseNotFound));
    }
}
