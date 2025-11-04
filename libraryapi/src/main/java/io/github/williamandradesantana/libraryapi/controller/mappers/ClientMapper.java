package io.github.williamandradesantana.libraryapi.controller.mappers;

import io.github.williamandradesantana.libraryapi.controller.dto.ClientDTO;
import io.github.williamandradesantana.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDTO clientDTO);
    ClientDTO toDTO(Client client);
}
