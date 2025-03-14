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
    private final String make;
    private final String model;
    private final int speed;
    private final Color color;    
    private final BigDecimal price;
    private final List<String> equipment;
}
