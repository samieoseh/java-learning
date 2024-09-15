package com.samuel.progresqldatabase.dao.impl;

import com.samuel.progresqldatabase.TestDataUtil;
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
public class AuthorDaoImplIntegrationTests {
    private  AuthorDaoImpl undertest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl undertest) {
        this.undertest = undertest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(3L);
        undertest.create(author);
        Optional<Author> result = undertest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public  void testThatMultipleAuthorCanBeCreatedAndRecalled() {
        int numberOfAuthors = 10;
        List<Author> authors = TestDataUtil.createMultipleTestAuthors(numberOfAuthors);

        // create authors in database
        for (Author author: authors) {
            undertest.create(author);
        }

        List<Author> results = undertest.find();
        assertThat(results).hasSize(numberOfAuthors);
        assertThat(results).containsAll(authors);


    }

    @Test
    public void testThatAuthorCanBeUpdatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(3L);

        undertest.create(author);
        undertest.update(author.getId(), author); // updates the author to "John Doe", 89

        Optional<Author> updatedAuthor = undertest.findOne(author.getId());
        System.out.println(updatedAuthor);

        assertThat(updatedAuthor).isPresent();
        assertThat(updatedAuthor.get()).isNotEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeletedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(1L);
        undertest.create(author);
        undertest.delete(author.getId());

        Optional<Author> result = undertest.findOne(author.getId());
        assertThat(result).isEmpty();
    }


}
