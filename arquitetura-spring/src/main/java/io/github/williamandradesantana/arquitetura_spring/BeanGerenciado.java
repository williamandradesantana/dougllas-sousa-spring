package io.github.williamandradesantana.arquitetura_spring;

import io.github.williamandradesantana.arquitetura_spring.todos.TodoEntity;
import io.github.williamandradesantana.arquitetura_spring.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

//@Lazy(false)
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
//@Scope(WebApplicationContext.SCOPE_REQUEST)
//@Scope("session")
//@Scope("request")
//@Scope("application")
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
