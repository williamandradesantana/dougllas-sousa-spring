package io.github.williamandradesantana.libraryapi.controller.mappers;

import io.github.williamandradesantana.libraryapi.controller.dto.UserDTO;
import io.github.williamandradesantana.libraryapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);
    UserDTO toDTO(User user);
}
