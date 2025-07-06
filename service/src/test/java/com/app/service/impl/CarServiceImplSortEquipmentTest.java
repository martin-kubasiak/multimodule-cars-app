package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static com.app.CarsUtil.*;
import static org.assertj.core.api.Assertions.*;

public class CarServiceImplSortEquipmentTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA_1);
    private static final CarService carService = new CarServiceImpl(CARS);


    @Test
    @DisplayName("when comparator is null")
    void test1() {
        assertThatThrownBy(() -> carService.sortEquipment(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @Test
    @DisplayName("when comparator is not null")
    void test2() {
        Comparator<String> comparator = Comparator.naturalOrder();
        var expectedCars = List.of(
                CAR_MAZDA.withSortedEquipment(comparator),
                CAR_TOYOTA_1.withSortedEquipment(comparator)
        );

        assertThat(carService.sortEquipment(comparator))
                .isEqualTo(expectedCars);
    }

}
