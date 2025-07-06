package com.app.mapper.car.impl;

import com.app.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CarWithSortedEquipmentTest {

    @Test
    @DisplayName("when list of equipment is sorted correctly")
    void test1() {
        var car = Car
                .builder()
                .equipment(List.of("C", "A", "B"))
                .build();

        var expectedCar = Car
                .builder()
                .equipment(List.of("A", "B", "C"))
                .build();

        assertThat(car.withSortedEquipment(Comparator.naturalOrder()))
                .isEqualTo(expectedCar);
    }
}
