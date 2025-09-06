package io.github.williamandradesantana.arquitetura_spring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoEntity salvar(TodoEntity entity) {
        return todoRepository.save(entity);
    }

    public void atualizarStatus(TodoEntity entity) {
        this.todoRepository.save(entity);
    }

    public TodoEntity buscar(Integer id) {
        return this.todoRepository.findById(id).orElse(null);
    }
}
