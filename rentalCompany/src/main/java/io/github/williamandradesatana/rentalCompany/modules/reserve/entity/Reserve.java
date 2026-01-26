package io.github.williamandradesatana.rentalCompany.modules.reserve.entity;

import io.github.williamandradesatana.rentalCompany.exceptions.InvalidReservationException;
import io.github.williamandradesatana.rentalCompany.modules.car.entity.Car;
import io.github.williamandradesatana.rentalCompany.modules.client.entity.Client;

public class Reserve {

    private Client client;
    private Car car;
    private Integer quantityDays;

    public Reserve(Client client, Car car, Integer quantityDays) {
        quantityDaysCannotBeLessThanOne(quantityDays);
        this.client = client;
        this.car = car;
        this.quantityDays = quantityDays;

    }

    private void quantityDaysCannotBeLessThanOne(Integer quantityDays) {
        if (quantityDays < 1) throw new InvalidReservationException();
    }
    public double calculateReservation() {
        return this.car.calculateRentPrice(this.quantityDays);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getQuantityDays() {
        return quantityDays;
    }

    public void setQuantityDays(Integer quantityDays) {
        this.quantityDays = quantityDays;
    }
}
