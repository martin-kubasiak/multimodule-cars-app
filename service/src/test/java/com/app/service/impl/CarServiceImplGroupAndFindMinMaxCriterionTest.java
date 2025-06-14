package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.model.Comparators;
import com.app.model.Mappers;
import com.app.service.CarService;
import com.app.util.MinMax;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.app.CarsUtil.*;
import static com.app.model.Comparators.*;
import static com.app.model.Mappers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplGroupAndFindMinMaxCriterionTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA_1, CAR_TOYOTA_2, CAR_BMW_1, CAR_BMW_2);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("should group by make and then find min and max by price")
    void test1() {
        assertThat(carService.groupAndFindMinMaxByCriterion(toMakeMapper, byPriceComparator))
                .containsAllEntriesOf(Map.of(
                        "MAZDA", new MinMax<>(CAR_MAZDA, CAR_MAZDA),
                        "TOYOTA", new MinMax<>(CAR_TOYOTA_1, CAR_TOYOTA_2),
                        "BMW", new MinMax<>(CAR_BMW_2, CAR_BMW_1)
                ));
    }

    @Test
    @DisplayName("should group by color and then find min and max speed")
    void test2() {
        assertThat(carService.groupAndFindMinMaxByCriterion(toColorMapper, bySpeedComparator))
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, new MinMax<>(CAR_MAZDA, CAR_BMW_2),
                        Color.BLUE, new MinMax<>(CAR_TOYOTA_1, CAR_TOYOTA_1),
                        Color.WHITE, new MinMax<>(CAR_TOYOTA_2, CAR_TOYOTA_2),
                        Color.GREEN, new MinMax<>(CAR_BMW_1, CAR_BMW_1)
                ));
    }

}
