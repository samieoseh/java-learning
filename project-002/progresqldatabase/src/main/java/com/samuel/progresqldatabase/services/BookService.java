package com.samuel.progresqldatabase.services;

import com.samuel.progresqldatabase.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity author);
}
