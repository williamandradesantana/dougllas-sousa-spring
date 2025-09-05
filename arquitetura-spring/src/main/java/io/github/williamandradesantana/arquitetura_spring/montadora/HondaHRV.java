package io.github.williamandradesantana.arquitetura_spring.montadora;

import java.awt.*;

public class HondaHRV extends Carro {
    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setColor(Color.BLACK);
        setMontadora(Montadora.HONDA);
    }
}
