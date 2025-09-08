package io.github.williamandradesantana.arquitetura_spring.montadora.configuration;

import io.github.williamandradesantana.arquitetura_spring.montadora.Motor;
import io.github.williamandradesantana.arquitetura_spring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "motorAspirado")
    @Primary
    @Scope("singleton")
    public Motor motorAspirado() {
        var motor = new Motor();

        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setLitragem(2.0);
        motor.setTipoMotor(TipoMotor.ASPIRADO);

        return motor;
    }

    @Bean(name = "motorEletrico")
    public Motor motorEletrico() {
        var motor = new Motor();

        motor.setCavalos(110);
        motor.setCilindros(3);
        motor.setModelo("TH-10");
        motor.setLitragem(1.4);
        motor.setTipoMotor(TipoMotor.ELETRICO);

        return motor;
    }

    @Bean(name = "motorTurbo")
    public Motor motorTurbo() {
        var motor = new Motor();

        motor.setCavalos(180);
        motor.setCilindros(4);
        motor.setModelo("XPTO-1");
        motor.setLitragem(1.5);
        motor.setTipoMotor(TipoMotor.TURBO);

        return motor;
    }
}
