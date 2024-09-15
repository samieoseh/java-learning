package com.samuel.progresqldatabase;

import com.samuel.progresqldatabase.domain.Author;
import com.samuel.progresqldatabase.domain.Book;

import java.util.ArrayList;
import java.util.List;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthor(long authorId) {
        return Author.builder()
                .id(authorId)
                .name("Samuel Oseh")
                .age(89)
                .build();

    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static List<Author> createMultipleTestAuthors(int numberOfAuthors) {
        List<Author> authors = new ArrayList<>();

        for (long i = 1; i <= numberOfAuthors; i++) {
            int intAge = (int)i;
            authors.add(Author.builder()
                    .id(i)
                    .name("Author " + i)
                    .age(intAge) // Assigning different ages for variety
                    .build());
        }

        return authors;
    }

    public static List<Book> createMultipleTestBooks(int numberOfBooks, Author author) {
        List<Book> books = new ArrayList<>();

        for (long i = 1; i <= numberOfBooks; i++) {
            books.add(Book.builder()
                    .isbn("isbn " + i)
                    .title("Title " + i)
                    .authorId(author.getId())
                    .build());
        }

        return books;
    }
}
