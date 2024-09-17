package com.samuel.progresqldatabase;

import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.domain.entities.BookEntity;

import java.util.ArrayList;
import java.util.List;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static AuthorEntity createTestAuthor(long authorId) {
        return AuthorEntity.builder()
                .id(authorId)
                .name("Samuel Oseh")
                .age(89)
                .build();

    }

    public static BookEntity createTestBook(AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static List<AuthorEntity> createMultipleTestAuthors(int numberOfAuthors) {
        List<AuthorEntity> authors = new ArrayList<>();

        for (long i = 1; i <= numberOfAuthors; i++) {
            int intAge = (int)i;
            authors.add(AuthorEntity.builder()
                    .id(i)
                    .name("Author " + i)
                    .age(intAge) // Assigning different ages for variety
                    .build());
        }

        return authors;
    }

    public static List<BookEntity> createMultipleTestBooks(int numberOfBooks, AuthorEntity author) {
        List<BookEntity> bookEntities = new ArrayList<>();

        for (long i = 1; i <= numberOfBooks; i++) {
            bookEntities.add(BookEntity.builder()
                    .isbn("isbn " + i)
                    .title("Title " + i)
                    .author(author)
                    .build());
        }

        return bookEntities;
    }
}
