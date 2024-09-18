package com.samuel.progresqldatabase.controllers;

import com.samuel.progresqldatabase.domain.dto.BookDto;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.domain.entities.BookEntity;
import com.samuel.progresqldatabase.mappers.Mapper;
import com.samuel.progresqldatabase.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookCtrl {

    private BookService bookService;
    private Mapper<BookEntity, BookDto> bookMapper;

    public BookCtrl(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        if(!bookService.isExists(isbn)) {
            bookDto.setIsbn(isbn);
            BookEntity updatedBookEntity = bookService.save(bookDto.getIsbn(), bookMapper.mapFrom(bookDto));
            return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);
        }
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.save(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity), HttpStatus.CREATED);
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> patchBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        if(!bookService.isExists(isbn)) {
            bookDto.setIsbn(isbn);
            BookEntity updatedBookEntity = bookService.save(bookDto.getIsbn(), bookMapper.mapFrom(bookDto));
            return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);
        }
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.patch(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public  ResponseEntity<List<BookDto>> getBooks() {
        List<BookEntity> bookEntities = bookService.getBooks();
        return new ResponseEntity<>(bookEntities.stream().map(bookMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> bookEntity = bookService.getBook(isbn);
        return bookEntity.map(book -> {
                BookDto bookDto = bookMapper.mapTo(book);
                return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.OK));
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable("isbn")String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
