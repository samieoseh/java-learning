package com.samuel.progresqldatabase.repositories;

import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AuthorRepo extends CrudRepository<AuthorEntity, Long> {


}
