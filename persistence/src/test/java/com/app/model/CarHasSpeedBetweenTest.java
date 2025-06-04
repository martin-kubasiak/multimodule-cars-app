package com.app.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.app.CarsUtil.*;

public class CarHasSpeedBetweenTest {

    // M-200, T-220, B-250
    private static Stream<Arguments> speedRangesWithCars() {
        return Stream.of(
                Arguments.of(100, 190, CAR_MAZDA, false),
                Arguments.of(200, 210, CAR_MAZDA, true),
                Arguments.of(200, 220, CAR_TOYOTA, true),
                Arguments.of(200, 250, CAR_TOYOTA, true),
                Arguments.of(220, 220, CAR_TOYOTA, true),
                Arguments.of(260, 300, CAR_BMW, false)
        );
    }

    @ParameterizedTest
    @MethodSource("speedRangesWithCars")
    @DisplayName("should check if car's speed is within the specified range")
    void test1(int speedMin, int speedMax, Car car, boolean isWithinRange) {
        Assertions
                .assertThat(car.hasSpeedBetween(speedMin, speedMax))
                .isEqualTo(isWithinRange);
    }


}
