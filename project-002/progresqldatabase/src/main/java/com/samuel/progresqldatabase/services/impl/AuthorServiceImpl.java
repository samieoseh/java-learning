package com.samuel.progresqldatabase.services.impl;

import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.repositories.AuthorRepo;
import com.samuel.progresqldatabase.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepo authorRepo;

    public  AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepo.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> getAuthors() {
        return StreamSupport.stream(authorRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
