package com.samuel.progresqldatabase.dao.impl;

import com.samuel.progresqldatabase.TestDataUtil;
import com.samuel.progresqldatabase.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import org.mockito.ArgumentMatchers;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthor(2L);
        underTest.create(author);

        verify(jdbcTemplate).update(eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"), eq(2L), eq("Samuel Oseh"), eq(89));


    }

    @Test
    public void testThatFindOneAuthorGeneratesTheCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id=? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyAuthorGeneratesTheCorrectSql() {
        underTest.find();
        verify(jdbcTemplate).query(
               eq("SELECT id, name, age FROM authors"),
               ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }
}
