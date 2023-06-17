package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.PublishingHouseService;
import com.axonactive.PersonalProject.service.dto.PublishingHouseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.valves.rewrite.RewriteCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/auth/publishingHouses")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")

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
        return ResponseEntity.created(URI.create("/api/publishingHouses/" + publishingHouse.getId())).body(publishingHouse);


    }
    @PutMapping(value = "/{publishID}")
    public ResponseEntity<PublishingHouseDTO> updatePublishingHouse (@PathVariable ("publishID") Long publishingHouseID ,@RequestBody PublishingHouseDTO publishingHouseDTO){
        PublishingHouseDTO publishingHouse = publishngHouseService.updatePublishingHouse(publishingHouseID,publishingHouseDTO);
        return ResponseEntity.created(URI.create("/api/publishingHouses/" + publishingHouse.getId())).body(publishingHouse);

    }
    @DeleteMapping(value = "/{publishingID}")
    public ResponseEntity<PublishingHouseDTO> deletePublishingHouse (@PathVariable("publishingID") Long publishingId){
        publishngHouseService.deletePublishingHouseByID(publishingId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{pubHouseId}")
    public ResponseEntity<PublishingHouseDTO>getPublishingHouseById(@PathVariable("pubHouseId") Long pubHouseId){
        return ResponseEntity.ok(publishngHouseService.getPublishingHouseById(pubHouseId));
    }

}
