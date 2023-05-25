package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Author;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.AuthorRepository;
import com.axonactive.PersonalProject.service.AuthorService;
import com.axonactive.PersonalProject.service.dto.AuthorDTO;
import com.axonactive.PersonalProject.service.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImplement implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDTO> getAllAuthor() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toDtos(authors);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setAuthorFirstName(authorDTO.getAuthorFirstName());
        author.setAuthorLastName(authorDTO.getAuthorLastName());
        author = authorRepository.save(author);
        return authorMapper.toDto(author);

    }


    @Override
    public AuthorDTO updateAuthor(Long authorID, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(authorID).orElseThrow(BookStoreException::AuthorNotFound);
        author.setAuthorFirstName(authorDTO.getAuthorFirstName());
        author.setAuthorLastName(authorDTO.getAuthorLastName());
        author = authorRepository.save(author);
        return authorMapper.toDto(author);
    }

    @Override
    public void deleteAuthorByID(Long authorID) {
        authorRepository.deleteById(authorID);

    }

    @Override
    public AuthorDTO getAuthorByID(Long authorID) {
        return authorMapper.toDto(authorRepository.findById(authorID).orElseThrow(BookStoreException::AuthorNotFound));
    }
    private static void exception (AuthorDTO authorDTO){
        if (authorDTO.getAuthorLastName().isBlank() || !isAlpha(authorDTO.getAuthorLastName())||
        authorDTO.getAuthorFirstName().isBlank() || !isAlpha(authorDTO.getAuthorFirstName()))
            throw BookStoreException.badRequest("Wrong format name", "Name should contain only letters");
    }
}
