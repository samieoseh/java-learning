package com.samuel.progresqldatabase.services.impl;

import com.samuel.progresqldatabase.domain.entities.BookEntity;
import com.samuel.progresqldatabase.repositories.BookRepo;
import com.samuel.progresqldatabase.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl  implements BookService {
    private BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepo.save(bookEntity);
    }

    @Override
    public List<BookEntity> getBooks() {
        return StreamSupport.stream(bookRepo.findAll().spliterator(), false).toList();
    }
}
