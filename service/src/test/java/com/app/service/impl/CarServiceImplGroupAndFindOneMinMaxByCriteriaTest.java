package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.service.CarService;
import com.app.util.MinMax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.app.CarsUtil.*;
import static com.app.model.Comparators.*;
import static com.app.model.Mappers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplGroupAndFindOneMinMaxByCriteriaTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA_1, CAR_TOYOTA_2,
            CAR_BMW_1, CAR_BMW_2);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when grouping function is null")
    void test1() {
        assertThatThrownBy(() ->
                carService.groupAndFindMinMaxByCriteria(null, byPriceComparator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grouping function is null");
    }

    @Test
    @DisplayName("when car comparator is null")
    void test2() {
        assertThatThrownBy(() ->
                carService.groupAndFindMinMaxByCriteria(toMakeMapper, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator function is null");
    }

    @Test
    @DisplayName("should group by make and then find one min and one max by price")
    void test3() {
        assertThat(carService.groupAndFindMinMaxByCriteria(toMakeMapper, byPriceComparator))
                .containsAllEntriesOf(Map.of(
                        "MAZDA", new MinMax<>(CAR_MAZDA, CAR_MAZDA),
                        "TOYOTA", new MinMax<>(CAR_TOYOTA_1, CAR_TOYOTA_2),
                        "BMW", new MinMax<>(CAR_BMW_2, CAR_BMW_1)
                ));
    }

    @Test
    @DisplayName("should group by color and then find one min and one max speed")
    void test4() {
        assertThat(carService.groupAndFindMinMaxByCriteria(toColorMapper, bySpeedComparator))
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, new MinMax<>(CAR_MAZDA, CAR_BMW_2),
                        Color.BLUE, new MinMax<>(CAR_TOYOTA_1, CAR_TOYOTA_1),
                        Color.WHITE, new MinMax<>(CAR_TOYOTA_2, CAR_TOYOTA_2),
                        Color.GREEN, new MinMax<>(CAR_BMW_1, CAR_BMW_1)
                ));
    }
}
