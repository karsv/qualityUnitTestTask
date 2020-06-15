package org.testtask.models;

import java.util.Comparator;
import java.util.Objects;

public class FoldingBike extends Bike implements Comparable<FoldingBike> {
    private double wheelsSize;
    private int numberOfGears;

    public double getWheelsSize() {
        return wheelsSize;
    }

    public void setWheelsSize(double wheelsSize) {
        this.wheelsSize = wheelsSize;
    }

    public int getNumberOfGears() {
        return numberOfGears;
    }

    public void setNumberOfGears(int numberOfGears) {
        this.numberOfGears = numberOfGears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoldingBike)) return false;
        if (!super.equals(o)) return false;
        FoldingBike that = (FoldingBike) o;
        return Double.compare(that.wheelsSize, wheelsSize) == 0 &&
                numberOfGears == that.numberOfGears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wheelsSize, numberOfGears);
    }

    @Override
    public String showAllParameters() {
        return "Type " + super.getType() + " " +
                "Brand " + super.getBrand() + " " +
                "Size of wheels " + this.getWheelsSize() + " " +
                "Number of gears " + this.getNumberOfGears() + " " +
                "Weight " + super.getWeight() + " " +
                "Lights " + super.isAvailableLights() + " " +
                "Color " + super.getColor() + " " +
                "Price " + super.getPrice();
    }

    @Override
    public int compareTo(FoldingBike o) {
        return Comparator.comparing(FoldingBike::getBrand)
                .thenComparing(FoldingBike::getPrice)
                .thenComparing(FoldingBike::getNumberOfGears)
                .thenComparing(FoldingBike::getWheelsSize)
                .thenComparing(FoldingBike::getColor)
                .thenComparing(FoldingBike::getWeight)
                .thenComparing(FoldingBike::isAvailableLights)
                .compare(this, o);
    }

    @Override
    public String toString() {
        return super.getType() + " " + super.getBrand()
                + " with " + numberOfGears + " gear(s) and "
                + (super.isAvailableLights() ? "" : "no") + " head/tail light.\n"
                + "Price: " + super.getPrice() + " euros.\n";
    }
}
