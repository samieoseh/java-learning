package com.samuel.progresqldatabase.repositories;

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
public class AuthorRepoIntegrationTests {
    private  AuthorRepo undertest;

    @Autowired
    public AuthorRepoIntegrationTests(AuthorRepo undertest) {
        this.undertest = undertest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(1L);
        undertest.save(author);
        Optional<Author> result = undertest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public  void testThatMultipleAuthorCanBeCreatedAndRecalled() {
        int numberOfAuthors = 10;
        List<Author> authors = TestDataUtil.createMultipleTestAuthors(numberOfAuthors);

        // create authors in database
        for (Author author: authors) {
            undertest.save(author);
        }

        Iterable<Author> results = undertest.findAll();
        assertThat(results).hasSize(numberOfAuthors);
        assertThat(results).containsAll(authors);
    }

    @Test
    public void testThatAuthorCanBeUpdatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(1L);

        undertest.save(author);
        author.setName("John Doe");
        undertest.save(author); // updates the author to "John Doe"

        Optional<Author> updatedAuthor = undertest.findById(author.getId());
        Iterable<Author> results = undertest.findAll();

        System.out.println(updatedAuthor);

        assertThat(updatedAuthor).isPresent();
        assertThat(results).hasSize(1);
        assertThat(updatedAuthor.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeletedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor(1L);
        undertest.save(author);
        undertest.deleteById(author.getId());

        Optional<Author> result = undertest.findById(author.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        List<Author> authors = TestDataUtil.createMultipleTestAuthors(10);
        List<Author> subsetAuthors = authors.subList(0, 4);

        for (Author author : authors) {
            undertest.save(author);
        }

        Iterable<Author> results= undertest.ageLessThan(5);

        assertThat(results).hasSize(4);
        assertThat(results).containsAll(subsetAuthors);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        List<Author> authors = TestDataUtil.createMultipleTestAuthors(10);
        List<Author> subsetAuthors = authors.subList(5, 10);

        for (Author author : authors) {
            undertest.save(author);
        }

        Iterable<Author> results= undertest.ageGreaterThan(5);
        // Iterable<Author> results= undertest.findAgeGreaterThanBlah(5);

        assertThat(results).hasSize(5);
        assertThat(results).containsAll(subsetAuthors);
    }
}
