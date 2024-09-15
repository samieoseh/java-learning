package com.samuel.progresqldatabase.repositories;

import com.samuel.progresqldatabase.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, String> {

}
