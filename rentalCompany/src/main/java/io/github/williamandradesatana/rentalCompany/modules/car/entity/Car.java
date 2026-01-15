package io.github.williamandradesatana.rentalCompany.modules.car.entity;

public class Car {
    private String model;
    private Double dailyPrice;

    public Car(){}
    public Car(String model, Double dailyPrice) {
        this.model = model;
        this.dailyPrice = dailyPrice;
    }

    public double calculateRentPrice(int quantityDays) {
        return quantityDays * getDailyPrice();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
}
