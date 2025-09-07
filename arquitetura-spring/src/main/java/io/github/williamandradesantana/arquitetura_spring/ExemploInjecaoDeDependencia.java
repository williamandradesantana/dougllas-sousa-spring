package io.github.williamandradesantana.arquitetura_spring;

import io.github.williamandradesantana.arquitetura_spring.todos.MailSender;
import io.github.williamandradesantana.arquitetura_spring.todos.TodoRepository;
import io.github.williamandradesantana.arquitetura_spring.todos.TodoService;
import io.github.williamandradesantana.arquitetura_spring.todos.TodoValidator;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;


public class ExemploInjecaoDeDependencia {
    public static void main(String[] args) throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("url");
        dataSource.setUsername("username");
        dataSource.setPassword("password");

        Connection connection = dataSource.getConnection();

        EntityManager entityManager = null;

        TodoRepository todoRepository = null; // new SimpleJpaRepository<TodoEntity, Integer>(null, null);
        TodoValidator todoValidator = new TodoValidator(todoRepository);
        MailSender mailSender = new MailSender();

        TodoService todoService = new TodoService(todoRepository, todoValidator, mailSender);

        BeanGerenciado beanGerenciado = new BeanGerenciado(null);
        beanGerenciado.setValidator(todoValidator);
    }
}
