package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.model.Predicates;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.app.CarsUtil.*;
import static com.app.model.Mappers.toColorMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplFindAllByTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA, CAR_BMW);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when cars do not have speed in given range")
    void test1() {
        Assertions.assertThat(carService.findAllBy(
                        Predicates.hasSpeedBetweenPredicate(10, 20)))
                .isEmpty();
    }

    @Test
    @DisplayName("when cars have speed in given range")
    void test2() {
        Assertions.assertThat(carService.findAllBy(
                        Predicates.hasSpeedBetweenPredicate(220, 300)))
                .isEqualTo(List.of(CAR_TOYOTA, CAR_BMW));
    }

}
