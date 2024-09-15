package com.samuel.progresqldatabase.repositories;

import com.samuel.progresqldatabase.TestDataUtil;
import com.samuel.progresqldatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.samuel.progresqldatabase.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepoIntegrationTests {
    private AuthorRepo authorDao;
    private  BookRepo undertest;

    @Autowired
    public BookRepoIntegrationTests(BookRepo undertest, AuthorRepo authorDao) {
        this.undertest = undertest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        Book book = TestDataUtil.createTestBook(author);
        System.out.println(book);
        undertest.save(book);
        Optional<Book> result = undertest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public  void testThatMultipleBookCanBeCreatedAndRecalled() {
        int numberOfBooks = 10;
        Author author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        Iterable<Book> books = TestDataUtil.createMultipleTestBooks(numberOfBooks, author);

        // create authors in database
        for (Book book: books) {
            undertest.save(book);
        }

        Iterable<Book> results = undertest.findAll();
        assertThat(results).hasSize(numberOfBooks);
        assertThat(results).containsAll(books);
    }

    @Test
    public void testThatBookCanBeUpdatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        Book book = TestDataUtil.createTestBook(author);
        undertest.save(book);
        book.setTitle("This is a book");
        undertest.save(book);

        Optional<Book> result = undertest.findById(book.getIsbn());
        Iterable<Book> results = undertest.findAll();
        assertThat(results).hasSize(1);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthor(1L);
        authorDao.save(author);
        Book book = TestDataUtil.createTestBook(author);
        undertest.save(book);

        undertest.deleteById(book.getIsbn());
        Optional<Book> result = undertest.findById(book.getIsbn());
        assertThat(result).isEmpty();
    }
}
