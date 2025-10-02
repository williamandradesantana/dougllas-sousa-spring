package io.github.williamandradesantana.libraryapi.controller.mappers;

import io.github.williamandradesantana.libraryapi.controller.dto.AuthorDTO;
import io.github.williamandradesantana.libraryapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "nationality", target = "nationality")
    Author toEntity(AuthorDTO authorDTO);
    AuthorDTO toDTO(Author author);
}
