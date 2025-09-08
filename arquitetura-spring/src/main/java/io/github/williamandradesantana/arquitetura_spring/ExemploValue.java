package io.github.williamandradesantana.arquitetura_spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExemploValue {

    @Value("${app.config.variavel}")
    private String variavel;

    public void imprimir() {
        System.out.println(variavel);
    }
}
