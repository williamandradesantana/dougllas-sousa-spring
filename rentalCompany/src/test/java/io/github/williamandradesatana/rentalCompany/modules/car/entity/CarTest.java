package io.github.williamandradesatana.rentalCompany.modules.car.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    @DisplayName("Must calculate the correct rental price.")
    void mustBeCalculateRentPrice() {
        var car = new Car("Sedan", 100.0);
        double total = car.calculateRentPrice(3);

        assertEquals(300, total);
    }
}
