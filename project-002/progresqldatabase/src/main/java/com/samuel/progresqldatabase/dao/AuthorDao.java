package com.samuel.progresqldatabase.dao;

import com.samuel.progresqldatabase.domain.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(long authorId, Author author);

    void delete(Long authorId);
}
