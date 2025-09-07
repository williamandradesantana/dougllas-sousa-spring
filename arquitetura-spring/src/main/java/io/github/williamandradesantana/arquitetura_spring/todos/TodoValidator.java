package io.github.williamandradesantana.arquitetura_spring.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    private final TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void validar(TodoEntity todo) {
        if (existeTodoComEssaDescricao(todo.getDescricao())) {
            throw new IllegalArgumentException("Já existe um TODO com essa descrição!");
        }
    }

    private boolean existeTodoComEssaDescricao(String descricao) {
        return this.todoRepository.existsByDescricao(descricao);
    }
}
