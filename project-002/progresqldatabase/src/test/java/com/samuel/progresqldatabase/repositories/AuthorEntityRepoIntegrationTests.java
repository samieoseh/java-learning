package com.samuel.progresqldatabase.repositories;

import com.samuel.progresqldatabase.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityRepoIntegrationTests {
    private  AuthorRepo undertest;

    @Autowired
    public AuthorEntityRepoIntegrationTests(AuthorRepo undertest) {
        this.undertest = undertest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);
        undertest.save(author);
        Optional<AuthorEntity> result = undertest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public  void testThatMultipleAuthorCanBeCreatedAndRecalled() {
        int numberOfAuthors = 10;
        List<AuthorEntity> authors = TestDataUtil.createMultipleTestAuthors(numberOfAuthors);

        // create authors in database
        for (AuthorEntity author: authors) {
            undertest.save(author);
        }

        Iterable<AuthorEntity> results = undertest.findAll();
        assertThat(results).hasSize(numberOfAuthors);
        assertThat(results).containsAll(authors);
    }

    @Test
    public void testThatAuthorCanBeUpdatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);

        undertest.save(author);
        author.setName("John Doe");
        undertest.save(author); // updates the author to "John Doe"

        Optional<AuthorEntity> updatedAuthor = undertest.findById(author.getId());
        Iterable<AuthorEntity> results = undertest.findAll();

        System.out.println(updatedAuthor);

        assertThat(updatedAuthor).isPresent();
        assertThat(results).hasSize(1);
        assertThat(updatedAuthor.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeletedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthor(1L);
        undertest.save(author);
        undertest.deleteById(author.getId());

        Optional<AuthorEntity> result = undertest.findById(author.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        List<AuthorEntity> authors = TestDataUtil.createMultipleTestAuthors(10);
        List<AuthorEntity> subsetAuthors = authors.subList(0, 4);

        for (AuthorEntity author : authors) {
            undertest.save(author);
        }

        Iterable<AuthorEntity> results= undertest.ageLessThan(5);

        assertThat(results).hasSize(4);
        assertThat(results).containsAll(subsetAuthors);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        List<AuthorEntity> authors = TestDataUtil.createMultipleTestAuthors(10);
        List<AuthorEntity> subsetAuthors = authors.subList(5, 10);

        for (AuthorEntity author : authors) {
            undertest.save(author);
        }

        Iterable<AuthorEntity> results= undertest.ageGreaterThan(5);

        assertThat(results).hasSize(5);
        assertThat(results).containsAll(subsetAuthors);
    }
}
