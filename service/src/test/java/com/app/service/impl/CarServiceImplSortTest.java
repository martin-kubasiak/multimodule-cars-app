package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.app.CarsUtil.*;
import static com.app.model.Comparators.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplSortTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA, CAR_BMW);
    private static final CarService carService = new CarServiceImpl(CARS);

    private static Stream<Arguments> comparatorsWithSortedCars() {
        return Stream.of(
                Arguments.of(byMakeComparator, List.of(CAR_BMW, CAR_MAZDA, CAR_TOYOTA)),
                Arguments.of(byMakeComparatorDesc, List.of(CAR_TOYOTA, CAR_MAZDA, CAR_BMW)),
                Arguments.of(byPriceComparator, List.of(CAR_MAZDA, CAR_TOYOTA, CAR_BMW)),
                Arguments.of(byPriceComparatorDesc, List.of(CAR_BMW, CAR_TOYOTA, CAR_MAZDA)),
                Arguments.of(bySpeedComparator, List.of(CAR_MAZDA, CAR_TOYOTA, CAR_BMW)),
                Arguments.of(getBySpeedComparatorComparatorDesc, List.of(CAR_BMW, CAR_TOYOTA, CAR_MAZDA))
        );
    }

    @Test
    @DisplayName("when comparator is null")
    void test1() {
        assertThatThrownBy(() -> carService.sort(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }


    @ParameterizedTest
    @MethodSource("comparatorsWithSortedCars")
    @DisplayName("when comparator is not null")
    void test2(Comparator<Car> carComparator, List<Car> expectedSortedCar) {
        assertThat(carService.sort(carComparator))
                .isEqualTo(expectedSortedCar);
    }

}
