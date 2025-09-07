package io.github.williamandradesantana.arquitetura_spring.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviar(String mensagem) {
        System.out.println("Enviado o email: " + mensagem);
    }
}
