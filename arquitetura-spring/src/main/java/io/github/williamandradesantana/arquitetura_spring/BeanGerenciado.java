package io.github.williamandradesantana.arquitetura_spring;

import io.github.williamandradesantana.arquitetura_spring.todos.TodoEntity;
import io.github.williamandradesantana.arquitetura_spring.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanGerenciado {
    @Autowired
    private TodoValidator todoValidator;

    public BeanGerenciado(TodoValidator todoValidator) {
        this.todoValidator = todoValidator;
    }

    public void utilizar() {
        var todo = new TodoEntity();
        todoValidator.validar(todo);
    }

    @Autowired
    public void setValidator(TodoValidator todoValidator) {
        this.todoValidator = todoValidator;
    }
}
