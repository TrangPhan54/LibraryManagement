package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthor ();
    AuthorDTO createAuthor (AuthorDTO authorDTO);
    AuthorDTO updateAuthor (Long authorID, AuthorDTO authorDTO);
    void deleteAuthorByID (Long authorID);
    AuthorDTO getAuthorByID (Long authorID);
    AuthorDTO getAuthorByFirstName (String firstName);
}
