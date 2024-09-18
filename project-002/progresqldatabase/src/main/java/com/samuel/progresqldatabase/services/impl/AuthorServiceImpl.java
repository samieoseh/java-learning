package com.samuel.progresqldatabase.services.impl;

import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.repositories.AuthorRepo;
import com.samuel.progresqldatabase.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepo authorRepo;

    public  AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepo.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> getAuthors() {
        return StreamSupport.stream(authorRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> getAuthor(Long id) {
        return authorRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepo.existsById(id);
    }

    @Override
    public AuthorEntity patch(AuthorEntity authorEntity) {
        Long id  = authorEntity.getId();
        return authorRepo.findById(id).map(
                existingAuthor -> {
                    Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
                    Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
                    return authorRepo.save(existingAuthor);
                }
        ).orElseThrow(() -> new RuntimeException("Author does not exist"));
    }

    @Override
    public void delete(Long id) {
         authorRepo.deleteById(id);
    }
}
