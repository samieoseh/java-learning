package com.samuel.progresqldatabase.services;

import com.samuel.progresqldatabase.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> getBooks();
}
