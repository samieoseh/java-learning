package com.samuel.progresqldatabase.controllers;

import com.samuel.progresqldatabase.domain.dto.AuthorDto;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.mappers.Mapper;
import com.samuel.progresqldatabase.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorCtrl {

    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;


    public AuthorCtrl(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorMapper = authorMapper;
        this.authorService = authorService;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping(path="/authors")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        List<AuthorEntity> authorEntitites = authorService.getAuthors();
        return new ResponseEntity<>(authorEntitites.stream().map(authorMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path="/authors/{id}")
    public  ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<AuthorEntity> foundAuthor = authorService.getAuthor(id);
        System.out.println(foundAuthor);
        return foundAuthor.map(authorEntity -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorDto updatedAuthorDto = authorMapper.mapTo(authorService.save(authorEntity));
        return new ResponseEntity<>(updatedAuthorDto, HttpStatus.OK);
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> patchAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorDto updatedAuthorDto = authorMapper.mapTo(authorService.patch(authorEntity));
        return new ResponseEntity<>(updatedAuthorDto, HttpStatus.OK);
    }


    @DeleteMapping(path = "authors/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
