package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.app.CarsUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplFindAllBySpeedBetweenTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA, CAR_BMW);
    private static final CarService carService = new CarServiceImpl(CARS);

    private static Stream<Arguments> speedRangesWithCars() {
        return Stream.of(
                Arguments.of(100, 190, List.of()),
                Arguments.of(200, 200, List.of(CAR_MAZDA)),
                Arguments.of(200, 210, List.of(CAR_MAZDA)),
                Arguments.of(200, 220, List.of(CAR_MAZDA, CAR_TOYOTA)),
                Arguments.of(200, 250, List.of(CAR_MAZDA, CAR_TOYOTA, CAR_BMW)),
                Arguments.of(250, 260, List.of(CAR_BMW)),
                Arguments.of(260, 300, List.of())
        );
    }

    @Test
    @DisplayName("when speed range is not correct")
    void test1() {
        assertThatThrownBy(() -> carService.findAllBySpeedBetween(200, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Speed range is not correct");
    }


    @ParameterizedTest
    @MethodSource("speedRangesWithCars")
    @DisplayName("when speed range is correct")
    void test2(int speedMin, int speedMax, List<Car> expectedCars) {
        assertThat(carService.findAllBySpeedBetween(speedMin, speedMax))
                .isEqualTo(expectedCars);
    }

}
