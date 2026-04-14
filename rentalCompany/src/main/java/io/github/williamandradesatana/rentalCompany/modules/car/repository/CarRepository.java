package io.github.williamandradesatana.rentalCompany.modules.car.repository;

import io.github.williamandradesatana.rentalCompany.modules.car.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
