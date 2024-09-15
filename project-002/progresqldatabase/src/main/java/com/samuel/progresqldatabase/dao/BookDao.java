package com.samuel.progresqldatabase.dao;

import com.samuel.progresqldatabase.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String s);

    List<Book> find();
}
