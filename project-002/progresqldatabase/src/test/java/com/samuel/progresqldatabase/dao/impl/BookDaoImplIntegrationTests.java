package com.samuel.progresqldatabase.dao.impl;

import com.samuel.progresqldatabase.TestDataUtil;
import com.samuel.progresqldatabase.dao.AuthorDao;
import com.samuel.progresqldatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.samuel.progresqldatabase.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {
    private AuthorDao authorDao;
    private  BookDaoImpl undertest;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl undertest, AuthorDao authorDao) {
        this.undertest = undertest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(1L);
        authorDao.create(author);
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        System.out.println(book);
        undertest.create(book);
        Optional<Book> result = undertest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public  void testThatMultipleBookCanBeCreatedAndRecalled() {
        int numberOfBooks = 10;
        Author author = TestDataUtil.createTestAuthor(4L);
        authorDao.create(author);
        List<Book> books = TestDataUtil.createMultipleTestBooks(numberOfBooks, author);

        // create authors in database
        for (Book book: books) {
            undertest.create(book);
        }

        List<Book> results = undertest.find();
        assertThat(results).hasSize(numberOfBooks);
        assertThat(results).containsAll(books);


    }
}
