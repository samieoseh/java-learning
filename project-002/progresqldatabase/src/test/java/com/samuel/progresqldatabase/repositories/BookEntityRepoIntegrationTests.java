package com.samuel.progresqldatabase.repositories;

import com.samuel.progresqldatabase.TestDataUtil;
import com.samuel.progresqldatabase.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepoIntegrationTests {
    private AuthorRepo authorDao;
    private  BookRepo undertest;

    @Autowired
    public BookEntityRepoIntegrationTests(BookRepo undertest, AuthorRepo authorDao) {
        this.undertest = undertest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        BookEntity bookEntity = TestDataUtil.createTestBook(author);
        System.out.println(bookEntity);
        undertest.save(bookEntity);
        Optional<BookEntity> result = undertest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public  void testThatMultipleBookCanBeCreatedAndRecalled() {
        int numberOfBooks = 10;
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        Iterable<BookEntity> books = TestDataUtil.createMultipleTestBooks(numberOfBooks, author);

        // create authors in database
        for (BookEntity bookEntity : books) {
            undertest.save(bookEntity);
        }

        Iterable<BookEntity> results = undertest.findAll();
        assertThat(results).hasSize(numberOfBooks);
        assertThat(results).containsAll(books);
    }

    @Test
    public void testThatBookCanBeUpdatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        BookEntity bookEntity = TestDataUtil.createTestBook(author);
        undertest.save(bookEntity);
        bookEntity.setTitle("This is a book");
        undertest.save(bookEntity);

        Optional<BookEntity> result = undertest.findById(bookEntity.getIsbn());
        Iterable<BookEntity> results = undertest.findAll();
        assertThat(results).hasSize(1);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        BookEntity bookEntity = TestDataUtil.createTestBook(author);
        undertest.save(bookEntity);

        undertest.deleteById(bookEntity.getIsbn());
        Optional<BookEntity> result = undertest.findById(bookEntity.getIsbn());
        assertThat(result).isEmpty();
    }
}
