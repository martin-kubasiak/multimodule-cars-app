package com.app.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class Car {
    final String make;
    final String model;
    final int speed;
    final Color color;
    final BigDecimal price;
    final List<String> equipment;

    /**
     * Checks whether this car's speed is within the specified inclusive range.
     *
     * @param speedMin the lower bound of the acceptable speed range
     * @param speedMax the upper bound of the acceptable speed range
     * @return true if the car's speed is between speedMin and speedMax (inclusive),
     *         otherwise false
     */
    public boolean hasSpeedBetween(int speedMin, int speedMax) {
        return speedMin <= speed && speed <= speedMax;
    }

    /**
     * Returns a new {@link Car} instance with its equipment list sorted according to the given comparator.
     *
     * @param equipmentComparator the comparator used to sort the equipment list; must not be null
     * @return a new {@link Car} instance with the equipment sorted
     * @throws NullPointerException if equipmentComparator is null
     */
    public Car withSortedEquipment(Comparator<String> equipmentComparator) {
        return Car.builder()
                .make(make)
                .model(model)
                .speed(speed)
                .color(color)
                .price(price)
                .equipment(equipment.stream().sorted(equipmentComparator).toList())
                .build();
    }

    /**
     * Calculates the absolute difference between this car's price and another given price.
     *
     * @param otherPrice the price to compare against; must not be null
     * @return the absolute difference as a {@link BigDecimal}
     * @throws NullPointerException if this car's price is null or if otherPrice is null
     */
    public BigDecimal calculatePriceDifference(BigDecimal otherPrice) {
        return this.price.subtract(otherPrice).abs();
    }
}
