package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Author;
import com.axonactive.PersonalProject.service.AuthorService;
import com.axonactive.PersonalProject.service.dto.AuthorDTO;
import lombok.Getter;
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
@RequestMapping(value = "/auth/authors")
@RequiredArgsConstructor
public class AuthorResource {

    @Autowired
    private final AuthorService authorService;
    @GetMapping
     public ResponseEntity<List<AuthorDTO>> getAllAuthor() {
        log.info("get all author");
        return ResponseEntity.ok(authorService.getAllAuthor());
    }


    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor (@RequestBody AuthorDTO authorDTO){
        log.info("create author");
        AuthorDTO author = authorService.createAuthor(authorDTO);
        return ResponseEntity.created(URI.create("/api/authors/" + author.getId())).body(author);
    }
    @PutMapping(value = "/{authorID}")
    public ResponseEntity<AuthorDTO> updateAuthor (@PathVariable ("authorID") Long authorID ,@RequestBody AuthorDTO authorDTO){
        log.info("update author by id {}",authorID);
        AuthorDTO author = authorService.updateAuthor(authorID,authorDTO);
        return ResponseEntity.created(URI.create("/api/authors/" + author.getId())).body(author);


    }
    @DeleteMapping(value = "/{authorID}")
    public ResponseEntity<AuthorDTO> deleteAuthor (@PathVariable("authorID") Long authorID){
        log.info("delete author by id {}",authorID);
        authorService.deleteAuthorByID(authorID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping (value = "/author_first_name")
    public ResponseEntity<AuthorDTO> getAuthorByFirstName (@RequestParam ("firstName") String firstName){
        log.info("get author by first name {}",firstName);

        return ResponseEntity.ok(authorService.getAuthorByFirstName(firstName));
    }
}
