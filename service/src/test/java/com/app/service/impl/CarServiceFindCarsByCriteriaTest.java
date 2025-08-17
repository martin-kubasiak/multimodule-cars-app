package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static com.app.CarsUtil.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarServiceFindCarsByCriteriaTest {

    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA_2, CAR_BMW_1);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when comparator is null")
    void test1() {
        assertThatThrownBy(() -> carService.findCarsByCriteria(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @Test
    @DisplayName("when comparing by price difference, should return all cars with the minimum difference")
    void test2() {
        var priceToCompare = BigDecimal.valueOf(8);
        Comparator<Car> carComparator = Comparator.comparing(car -> car.calculatePriceDifference(priceToCompare));
        var foundCars = carService.findCarsByCriteria(carComparator);
        assertThat(foundCars)
                .isEqualTo(List.of(CAR_TOYOTA_2, CAR_BMW_1));
    }
}
