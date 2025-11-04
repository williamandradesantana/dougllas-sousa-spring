package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.model.Client;
import io.github.williamandradesantana.libraryapi.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public Client create(Client client) {
        client.setClientSecret(encoder.encode(client.getClientSecret()));
        return clientRepository.save(client);
    }

    public Client getByClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }
}
