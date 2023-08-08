package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Author;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.AuthorRepository;
import com.axonactive.PersonalProject.service.AuthorService;
import com.axonactive.PersonalProject.service.dto.AuthorDTO;
import com.axonactive.PersonalProject.service.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImplementation implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDTO> getAllAuthor() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toDtos(authors);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        if (authorDTO.getLastName().isBlank() || !isAlpha(authorDTO.getLastName()) ||
                authorDTO.getFirstName().isBlank() || !isAlpha(authorDTO.getFirstName())) {
            throw LibraryException.badRequest("Wrong format name", "Name should contain only letters");
        }

        if (isDuplicated(authorDTO.getFirstName(), authorDTO.getLastName())) {
            throw LibraryException.badRequest("Duplicated author", "Name should be different");
        }

        Author author = Author.builder()
                .lastName(authorDTO.getLastName())
                .firstName(authorDTO.getFirstName())
                .build();

        author = authorRepository.save(author);
        return authorMapper.toDto(author);
    }
    // method to check if an author name is already existed
    private boolean isDuplicated(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstName(firstName).isPresent() && authorRepository.findAuthorByLastName(lastName).isPresent();
    }


    @Override
    public AuthorDTO updateAuthor(Long authorID, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(authorID).orElseThrow(LibraryException::AuthorNotFound);
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author = authorRepository.save(author);
        return authorMapper.toDto(author);
    }

    @Override
    public void deleteAuthorByID(Long authorID) {
        Author author = authorRepository.findById(authorID).orElseThrow(LibraryException::AuthorNotFound);
        authorRepository.delete(author);

    }

    @Override
    public AuthorDTO getAuthorByID(Long authorID) {
        return authorMapper.toDto(authorRepository.findById(authorID).orElseThrow(LibraryException::AuthorNotFound));
    }

    @Override
    public AuthorDTO getAuthorByFirstName(String firstName) {
        return null;
    }
}
