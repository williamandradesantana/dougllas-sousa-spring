package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.ClientDTO;
import io.github.williamandradesantana.libraryapi.controller.dto.ClientResponseDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.ClientMapper;
import io.github.williamandradesantana.libraryapi.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController implements GenericController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping("/")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Object> save(@Valid @RequestBody ClientDTO dto) {
        log.info("Registrando novo Client: {} - scope: {}", dto.clientId(), dto.scope());
        var client = clientMapper.toEntity(dto);
        clientService.create(client);

        URI location = createHeaderLocation(client.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> get(@PathVariable("clientId") String clientId) {
        var client = clientService.getByClientId(clientId);
        var dto = clientMapper.toDTO(client);
        return ResponseEntity.ok().body(dto);
    }
}
