package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.model.Mappers;
import com.app.service.CarService;
import com.app.util.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.app.CarsUtil.*;
import static com.app.model.Mappers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CarServiceImplGetStatisticsTest {
    private static final List<Car> CARS = List.of(CAR_MAZDA, CAR_TOYOTA_1, CAR_BMW_1);
    private static final CarService carService = new CarServiceImpl(CARS);

    private static Stream<Arguments> keyExtractorsWithStatistics() {
        return Stream.of(
                Arguments.of(toColorMapper,
                        new Statistics<>(Color.BLACK, Color.GREEN, null)
                ),
                Arguments.of(toMakeMapper,
                        new Statistics<>("BMW", "TOYOTA", null)
                ),
                Arguments.of(toPriceMapper,
                        new Statistics<>(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(4))
                ),
                Arguments.of(toSpeedMapper,
                        new Statistics<>(200, 250,
                                new BigDecimal("223"))
                )
        );
    }

    @Test
    @DisplayName("when keyExtractor is null")
    void test1() {
        assertThatThrownBy(() -> carService.getStatistics(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("KeyExtractor is null");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @ParameterizedTest
    @MethodSource("keyExtractorsWithStatistics")
    @DisplayName("when keyExtractor is not null")
    void test2(Function keyExtractor, Statistics<?> statistics) {
        assertThat(carService.getStatistics(keyExtractor))
                .isEqualTo(statistics);
    }

}
