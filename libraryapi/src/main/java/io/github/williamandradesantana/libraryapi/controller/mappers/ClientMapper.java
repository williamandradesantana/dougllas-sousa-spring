package io.github.williamandradesantana.libraryapi.controller.mappers;

import io.github.williamandradesantana.libraryapi.controller.dto.ClientDTO;
import io.github.williamandradesantana.libraryapi.controller.dto.ClientResponseDTO;
import io.github.williamandradesantana.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDTO clientDTO);
    ClientResponseDTO toDTO(Client client);
}
