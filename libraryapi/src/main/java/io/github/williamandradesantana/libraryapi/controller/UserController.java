package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.UserDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.UserMapper;
import io.github.williamandradesantana.libraryapi.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements GenericController {

    private final UserServices userServices;
    private final UserMapper userMapper;

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody @Valid UserDTO dto) {
        var user = userMapper.toEntity(dto);
        userServices.create(user);
        URI location = createHeaderLocation(user.getId());
        return ResponseEntity.created(location).build();
    }
}