package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.GenreService;
import com.axonactive.PersonalProject.service.ResponseService;
import com.axonactive.PersonalProject.service.dto.GenreDTO;
import com.axonactive.PersonalProject.service.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/auth/responses")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('USER')")
public class ResponseResource {
    @Autowired
    private final ResponseService responseService;

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllResponse() {
        return ResponseEntity.ok(responseService.getAllResponse());
    }


    @PostMapping
    public ResponseEntity<ResponseDTO> createResponse (@PathVariable("customerID") Long customerID,
                                                       @PathVariable("bookID") Long bookID,
            @RequestBody ResponseDTO responseDTO) {
        ResponseDTO response = responseService.createResponse(customerID, bookID, responseDTO);
        return ResponseEntity.created(URI.create("/api/responses/" + response.getResponseID())).body(response);


    }

    @PutMapping(value = "/{responseId}")
    public ResponseEntity<ResponseDTO> updateResponse(@PathVariable("responseId") Long responseID, @RequestBody ResponseDTO responseDTO) {
        ResponseDTO response = responseService.updateResponse(responseID, responseDTO);
        return ResponseEntity.created(URI.create("/api/responses/" + response.getResponseID())).body(response);
    }

    @DeleteMapping(value = "/{resID}")
    public ResponseEntity<ResponseDTO> deleteResponse(@PathVariable("resID") Long responseID) {
        responseService.deleteResponseByID(responseID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{responseId}")
    public ResponseEntity<ResponseDTO> getResponseById (@PathVariable("responseId") Long responseId){
        return ResponseEntity.ok(responseService.getResponseById(responseId));
    }
    @GetMapping("/response")
    public String findResponseOfABookByBookName(@RequestParam String bookName){
        return responseService.findResponseOfABookByBookName(bookName);
    }
}
