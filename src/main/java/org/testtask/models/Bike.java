package org.testtask.models;

import java.util.Objects;

public abstract class Bike {
    private String type;
    private String brand;
    private double weight;
    private boolean availableLights;
    private String color;
    private double price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isAvailableLights() {
        return availableLights;
    }

    public void setAvailableLights(boolean availableLights) {
        this.availableLights = availableLights;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Double.compare(bike.weight, weight) == 0 &&
                availableLights == bike.availableLights &&
                Double.compare(bike.price, price) == 0 &&
                Objects.equals(type, bike.type) &&
                Objects.equals(brand, bike.brand) &&
                Objects.equals(color, bike.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand, weight, availableLights, color, price);
    }

    public abstract String showAllParameters();
}
