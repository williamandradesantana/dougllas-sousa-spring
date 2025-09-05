package io.github.williamandradesantana.arquitetura_spring.montadora.configuration;

import io.github.williamandradesantana.arquitetura_spring.montadora.Motor;
import io.github.williamandradesantana.arquitetura_spring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MontadoraConfiguration {

    @Bean
    public Motor motor() {
        var motor = new Motor();

        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setLitragem(2.0);
        motor.setTipoMotor(TipoMotor.ASPIRADO);

        return motor;
    }
}
