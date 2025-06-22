package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.service.CarService;
import com.app.util.MinMax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.app.CarsUtil.*;
import static com.app.model.Comparators.byPriceComparator;
import static com.app.model.Comparators.bySpeedComparator;
import static com.app.model.Mappers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplGroupAndFindManyMinMaxByCriteriaTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA_1, CAR_TOYOTA_2, CAR_TOYOTA_3,
            CAR_BMW_1, CAR_BMW_2);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when grouping function is null")
    void test1() {
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                null, toPriceMapper, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grouping function is null");
    }

    @Test
    @DisplayName("when minMax grouping function is null")
    void test2() {
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                toMakeMapper, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Min max grouping function is null");
    }

    @Test
    @DisplayName("when minMax comparator function is null")
    void test3() {
        assertThatThrownBy(() -> carService.groupAndFindMinMaxByCriteria(
                toMakeMapper, toPriceMapper, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator function is null");
    }


    @Test
    @DisplayName("should group by make and then find at least one min and at least one max by price")
    void test4() {
        assertThat(carService.groupAndFindMinMaxByCriteria(toMakeMapper, toPriceMapper,
                Comparator.naturalOrder()))
                .containsAllEntriesOf(Map.of(
                        "MAZDA", new MinMax<>(List.of(CAR_MAZDA), List.of(CAR_MAZDA)),
                        "TOYOTA", new MinMax<>(List.of(CAR_TOYOTA_1), List.of(CAR_TOYOTA_2, CAR_TOYOTA_3)),
                        "BMW", new MinMax<>(List.of(CAR_BMW_2), List.of(CAR_BMW_1))
                ));
    }

    @Test
    @DisplayName("should group by color and then find at least one min and at least one max by speed")
    void test5() {
        assertThat(carService.groupAndFindMinMaxByCriteria(
                toColorMapper, toSpeedMapper, Comparator.naturalOrder()))
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, new MinMax<>(List.of(CAR_MAZDA), List.of(CAR_BMW_2)),
                        Color.BLUE, new MinMax<>(List.of(CAR_TOYOTA_1), List.of(CAR_TOYOTA_1)),
                        Color.WHITE, new MinMax<>(List.of(CAR_TOYOTA_2, CAR_TOYOTA_3),
                                List.of(CAR_TOYOTA_2, CAR_TOYOTA_3)),
                        Color.GREEN, new MinMax<>(List.of(CAR_BMW_1), List.of(CAR_BMW_1))
                ));
    }

}
