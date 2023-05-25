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
//@PreAuthorize("hasRole('ADMIN')")
public class AuthorResource {

    @Autowired
    private final AuthorService authorService;
    @GetMapping
     public ResponseEntity<List<AuthorDTO>> getAllAuthor() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }


    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor (@RequestBody AuthorDTO authorDTO){
        AuthorDTO author = authorService.createAuthor(authorDTO);
        return ResponseEntity.created(URI.create("/api/authors/" + author.getAuthorID())).body(author);


    }
    @PutMapping(value = "/{authorID}")
    public ResponseEntity<AuthorDTO> updateAuthor (@PathVariable ("authorID") Long authorID ,@RequestBody AuthorDTO authorDTO){
        AuthorDTO author = authorService.updateAuthor(authorID,authorDTO);
        return ResponseEntity.created(URI.create("/api/authors/" + author.getAuthorID())).body(author);


    }
    @DeleteMapping(value = "/{authorID}")
    public ResponseEntity<AuthorDTO> deleteAuthor (@PathVariable("authorID") Long authorID){
        authorService.deleteAuthorByID(authorID);
        return ResponseEntity.noContent().build();
    }
}
