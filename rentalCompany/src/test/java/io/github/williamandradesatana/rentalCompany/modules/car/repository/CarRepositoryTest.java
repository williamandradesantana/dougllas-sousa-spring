package io.github.williamandradesatana.rentalCompany.modules.car.repository;

import io.github.williamandradesatana.rentalCompany.modules.car.entity.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarRepositoryTest {
    @Autowired
    private CarRepository repository;

    @Test
    void mustBeSaveCarCorrectly() {
        CarEntity car = new CarEntity("Sedan", 150.0);

        var result = repository.save(car);
        assertNotNull(result.getId());
        assertEquals(150.0, result.getDailyPrice());
        assertEquals("Sedan", result.getModel());
    }
}