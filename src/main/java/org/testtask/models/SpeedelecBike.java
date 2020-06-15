package org.testtask.models;

import java.util.Comparator;
import java.util.Objects;

public class SpeedelecBike extends Bike implements Comparable<SpeedelecBike> {
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
        if (!(o instanceof SpeedelecBike)) return false;
        if (!super.equals(o)) return false;
        SpeedelecBike that = (SpeedelecBike) o;
        return Double.compare(that.maxSpeed, maxSpeed) == 0 &&
                Double.compare(that.batteryCapacity, batteryCapacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);
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

    @Override
    public int compareTo(SpeedelecBike speedelecBike) {
        return Comparator.comparing(SpeedelecBike::getBrand)
                .thenComparing(SpeedelecBike::getPrice)
                .thenComparing(SpeedelecBike::getMaxSpeed)
                .thenComparing(SpeedelecBike::getBatteryCapacity)
                .thenComparing(SpeedelecBike::getMaxSpeed)
                .thenComparing(SpeedelecBike::getWeight)
                .thenComparing(SpeedelecBike::isAvailableLights)
                .thenComparing(SpeedelecBike::getColor)
                .compare(this, speedelecBike);
    }

    @Override
    public String toString() {
        return super.getType() + " " + super.getBrand()
                + " with " + batteryCapacity + " mAh battery and "
                + (super.isAvailableLights() ? "" : "no") + " head/tail light.\n"
                + "Price: " + super.getPrice() + " euros.\n";
    }
}
