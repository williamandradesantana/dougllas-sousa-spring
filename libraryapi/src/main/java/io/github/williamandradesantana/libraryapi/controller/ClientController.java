package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.ClientDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.ClientMapper;
import io.github.williamandradesantana.libraryapi.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController implements GenericController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping("/")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Object> save(@Valid @RequestBody ClientDTO dto) {
        var client = clientMapper.toEntity(dto);
        clientService.create(client);

        URI location = createHeaderLocation(client.getId());
        return ResponseEntity.created(location).build();
    }
}
