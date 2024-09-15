package com.samuel.progresqldatabase.dao.impl;

import com.samuel.progresqldatabase.dao.AuthorDao;
import com.samuel.progresqldatabase.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl  implements AuthorDao  {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final  JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)", author.getId(), author.getName(), author.getAge());
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> results = jdbcTemplate.query("SELECT id, name, age FROM authors WHERE id=? LIMIT 1",
                new AuthorRowMapper(), authorId);

        return results.stream().findFirst();
    }

    @Override
    public List<Author> find() {
         return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                    new AuthorRowMapper()
                );
    }

    public void update(long authorId, Author author) {
        jdbcTemplate.update("UPDATE authors SET name = ?, age = ? WHERE id = ?",
                "John Doe", 89, authorId);

    }

    @Override
    public void delete(Long authorId) {
        jdbcTemplate.update(
                "DELETE FROM authors WHERE id = ?",
                authorId
        );
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
