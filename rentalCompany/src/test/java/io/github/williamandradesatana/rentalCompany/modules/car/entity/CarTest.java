package io.github.williamandradesatana.rentalCompany.modules.car.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    @DisplayName("Must calculate the correct rental price.")
    void mustBeCalculateRentPrice() {
        // 1. Cenário
        var car = new Car("Sedan", 100.0);

        // 2. Execução
        double total = car.calculateRentPrice(3);

        // 3. Verificação
        assertEquals(300, total);
    }

    @Test
    @DisplayName("Must calculate the correct rental price with discount.")
    void mustBeCalculateRentPriceWithDiscount() {
        // 1. Cenário
        var car = new Car("Sedan", 300.0);

        // 2. Execução
        double total = car.calculateRentPrice(5);

        // 3. Verificação
        assertEquals(1450.0, total);
    }
}
