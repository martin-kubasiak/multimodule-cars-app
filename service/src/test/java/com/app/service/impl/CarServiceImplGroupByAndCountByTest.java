package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.app.CarsUtil.*;
import static com.app.model.Mappers.toColorMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplGroupByAndCountByTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA_1, CAR_BMW_1);
    private static final CarService carService = new CarServiceImpl(CARS);

    @Test
    @DisplayName("when cars are grouped by classifier correctly")
    void test1() {
        Assertions.assertThat(carService.groupBy(toColorMapper))
                .hasSize(3)
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, List.of(CAR_MAZDA),
                        Color.BLUE, List.of(CAR_TOYOTA_1),
                        Color.GREEN, List.of(CAR_BMW_1)
                ));
    }

    @Test
    @DisplayName("when cars are counted by classifier correctly")
    void test2() {
        Assertions.assertThat(carService.countBy(toColorMapper))
                .hasSize(3)
                .containsAllEntriesOf(Map.of(
                        Color.BLACK, 1L,
                        Color.BLUE, 1L,
                        Color.GREEN, 1L
                ));
    }


}
