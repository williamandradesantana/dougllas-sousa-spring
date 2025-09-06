package io.github.williamandradesantana.arquitetura_spring.todos;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/")
    public TodoEntity salvar(@RequestBody TodoEntity entity) {
        return this.todoService.salvar(entity);
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
