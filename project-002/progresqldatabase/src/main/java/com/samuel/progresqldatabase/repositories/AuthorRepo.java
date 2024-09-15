package com.samuel.progresqldatabase.repositories;

import com.samuel.progresqldatabase.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {

    Iterable<Author> ageLessThan(int age);

    Iterable<Author> ageGreaterThan(int age);

    @Query("SELECT a FROM Author a where a.age > ?1")
    Iterable<Author> findAgeGreaterThanBlah(int age);
}
