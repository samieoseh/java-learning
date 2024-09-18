package com.samuel.progresqldatabase.services.impl;

import com.samuel.progresqldatabase.domain.entities.BookEntity;
import com.samuel.progresqldatabase.repositories.BookRepo;
import com.samuel.progresqldatabase.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl  implements BookService {
    private BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public BookEntity save(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepo.save(bookEntity);
    }

    @Override
    public List<BookEntity> getBooks() {
        return StreamSupport.stream(bookRepo.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<BookEntity> getBook(String isbn) {
        return bookRepo.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return false;
    }

    @Override
    public BookEntity patch(String isbn, BookEntity bookEntity) {
        return  bookRepo.findById(bookEntity.getIsbn()).map(
                existingBook ->  {
                    Optional.ofNullable(existingBook.getTitle()).ifPresent(existingBook::setTitle);
                    Optional.ofNullable(existingBook.getAuthor()).ifPresent(existingBook::setAuthor);
                    return bookRepo.save(existingBook);
                }
        ).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public void delete(String isbn) {
        bookRepo.deleteById(isbn);
    }
}
