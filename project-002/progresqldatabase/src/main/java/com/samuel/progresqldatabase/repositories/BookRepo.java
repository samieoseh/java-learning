package com.samuel.progresqldatabase.repositories;

import com.samuel.progresqldatabase.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<BookEntity, String> {

}
