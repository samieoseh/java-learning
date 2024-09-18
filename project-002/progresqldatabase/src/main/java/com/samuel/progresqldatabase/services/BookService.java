package com.samuel.progresqldatabase.services;

import com.samuel.progresqldatabase.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(String isbn, BookEntity book);

    List<BookEntity> getBooks();

    Optional<BookEntity> getBook(String isbn);

    boolean isExists(String isbn);

    BookEntity patch(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
