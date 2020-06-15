package org.testtask.models;

import java.util.Comparator;
import java.util.Objects;

public class Ebike extends Bike implements Comparable<Ebike> {
    private double maxSpeed;
    private double batteryCapacity;

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ebike)) return false;
        if (!super.equals(o)) return false;
        Ebike ebike = (Ebike) o;
        return Double.compare(ebike.maxSpeed, maxSpeed) == 0 &&
                Double.compare(ebike.batteryCapacity, batteryCapacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);
    }

    @Override
    public int compareTo(Ebike ebike) {
        return Comparator.comparing(Ebike::getBrand)
                .thenComparing(Ebike::getPrice)
                .thenComparing(Ebike::getMaxSpeed)
                .thenComparing(Ebike::getBatteryCapacity)
                .thenComparing(Ebike::getMaxSpeed)
                .thenComparing(Ebike::getWeight)
                .thenComparing(Ebike::isAvailableLights)
                .thenComparing(Ebike::getColor)
                .compare(this, ebike);
    }

    @Override
    public String toString() {
        return super.getType() + " " + super.getBrand()
                + " with " + batteryCapacity + " mAh battery and "
                + (super.isAvailableLights() ? "" : "no") + " head/tail light.\n"
                + "Price: " + super.getPrice() + " euros.\n";
    }

    @Override
    public String showAllParameters() {
        return "Type " + super.getType() + " " +
                "Brand " + super.getBrand() + " " +
                "MaxSpeed " + this.getMaxSpeed() + " " +
                "Weight " + super.getWeight() + " " +
                "Lights " + super.isAvailableLights() + " " +
                "Battery capacity " + this.getBatteryCapacity() + " " +
                "Color " + super.getColor() + " " +
                "Price " + super.getPrice();
    }


}
