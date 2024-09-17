package com.samuel.progresqldatabase.mappers.impl;

import com.samuel.progresqldatabase.domain.dto.AuthorDto;
import com.samuel.progresqldatabase.domain.entities.AuthorEntity;
import com.samuel.progresqldatabase.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {

    private ModelMapper modelMapper;

    public  AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return  modelMapper.map(authorDto, AuthorEntity.class);
    }
}
