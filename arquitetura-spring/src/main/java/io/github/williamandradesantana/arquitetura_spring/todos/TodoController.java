package io.github.williamandradesantana.arquitetura_spring.todos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/")
    public TodoEntity salvar(@RequestBody TodoEntity entity) {
        try {
            return this.todoService.salvar(entity);
        } catch (IllegalArgumentException e) {
            var mensagemErro = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, mensagemErro);
        }
    }

    @PutMapping("/{id}")
    public void atualizarStatus(@PathVariable("id") Integer id, @RequestBody TodoEntity entity) {
        entity.setId(id);
        this.todoService.atualizarStatus(entity);
    }

    @GetMapping("/{id}")
    public TodoEntity buscar(@PathVariable("id") Integer id) {
        return this.todoService.buscar(id);
    }
}
