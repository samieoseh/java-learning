package com.samuel.progresqldatabase.dao;

import com.samuel.progresqldatabase.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);
}
