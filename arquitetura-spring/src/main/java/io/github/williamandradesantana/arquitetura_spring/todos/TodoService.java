package io.github.williamandradesantana.arquitetura_spring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoValidator todoValidator;
    private final MailSender mailSender;

    public TodoService(TodoRepository todoRepository, TodoValidator todoValidator, MailSender mailSender) {
        this.todoRepository = todoRepository;
        this.todoValidator = todoValidator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity entity) {
        this.todoValidator.validar(entity);
        return this.todoRepository.save(entity);
    }

    public void atualizarStatus(TodoEntity entity) {
        this.todoRepository.save(entity);
        String status = (entity.getConcluido() == Boolean.TRUE) ? "Concluído" : "Não concluído";
        mailSender.enviar("TODO: " + entity.getDescricao() + " foi atualizado para " + status);
    }

    public TodoEntity buscar(Integer id) {
        return this.todoRepository.findById(id).orElse(null);
    }
}
