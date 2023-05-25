package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.PublishingHouseService;
import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/publishingHouses")
@RequiredArgsConstructor
public class PublishingHouseResource {
    @Autowired
    private final PublishingHouseService publishngHouseService;
    @GetMapping
    public ResponseEntity<List<PublishingHouseDTO>> getAllPublishingHouse() {
        return ResponseEntity.ok(publishngHouseService.getAllPublishingHouse());
    }
    @GetMapping("/get_by_name")
    public ResponseEntity<List<PublishingHouseDTO>> getPublishingHouseByName (@RequestParam ("name") String name){
        return ResponseEntity.ok().body(publishngHouseService.getPublishingHouseByName(name));
    }


    @PostMapping
    public ResponseEntity<PublishingHouseDTO> createPublishingHouse (@RequestBody PublishingHouseDTO publishingHouseDTO){
        PublishingHouseDTO publishingHouse = publishngHouseService.createPublishingHouse(publishingHouseDTO);
        return ResponseEntity.created(URI.create("/api/publishingHouses/" + publishingHouse.getPublishingHouseID())).body(publishingHouse);


    }
    @PutMapping(value = "/{publishID}")
    public ResponseEntity<PublishingHouseDTO> updatePublishingHouse (@PathVariable ("authorID") Long publishingHouseID ,@RequestBody PublishingHouseDTO publishingHouseDTO){
        PublishingHouseDTO publishingHouse = publishngHouseService.updatePublishingHouse(publishingHouseID,publishingHouseDTO);
        return ResponseEntity.created(URI.create("/api/publishingHouses/" + publishingHouse.getPublishingHouseID())).body(publishingHouse);

    }
    @DeleteMapping(value = "/{publishingID}")
    public ResponseEntity<PublishingHouseDTO> deletePublishingHouse (@PathVariable("publishingID") Long publishingId){
        publishngHouseService.deletePublishingHouseByID(publishingId);
        return ResponseEntity.noContent().build();
    }

}
