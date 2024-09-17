package com.samuel.progresqldatabase.controllers;

import com.samuel.progresqldatabase.domain.dto.AuthorDto;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.mappers.Mapper;
import com.samuel.progresqldatabase.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping(path="/authors")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        List<AuthorEntity> authorEntitites = authorService.getAuthors();
        return new ResponseEntity<>(authorEntitites.stream().map(authorMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }


}
