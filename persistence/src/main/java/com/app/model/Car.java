package com.app.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

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

    public boolean hasSpeedBetween(int speedMin, int speedMax) {
        return speedMin <= speed && speed <= speedMax;
    }
}
