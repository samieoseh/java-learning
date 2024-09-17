package com.samuel.progresqldatabase.services;

import com.samuel.progresqldatabase.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);
    List<AuthorEntity> getAuthors();
}
