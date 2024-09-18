package com.samuel.progresqldatabase.services;

import com.samuel.progresqldatabase.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity author);
    List<AuthorEntity> getAuthors();

    Optional<AuthorEntity> getAuthor(Long id);

    boolean isExists(Long id);

    AuthorEntity patch(AuthorEntity authorEntity);

    void delete(Long id);
}
