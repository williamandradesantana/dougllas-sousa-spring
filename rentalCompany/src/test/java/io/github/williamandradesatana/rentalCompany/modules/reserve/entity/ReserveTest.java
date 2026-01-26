package io.github.williamandradesatana.rentalCompany.modules.reserve.entity;

import io.github.williamandradesatana.rentalCompany.exceptions.InvalidReservationException;
import io.github.williamandradesatana.rentalCompany.modules.car.entity.Car;
import io.github.williamandradesatana.rentalCompany.modules.client.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ReserveTest {

    Car car;
    Client client;

    @BeforeEach
    void setUp() {
        car = new Car("Sedan", 100.0);
        client = new Client("Jon");
    }


    @Test
    void mustBeCreateReservation() {
        // Scenario
        var quantityDays = 3;

        // Execution
        var reserve = new Reserve(client, car, quantityDays);

        // Verification
        assertNotNull(reserve);
        assertThat(reserve.getClient().getName()).isEqualTo("Jon");
    }
}
