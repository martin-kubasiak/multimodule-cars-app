package com.app.validation.impl;

import com.app.config.AppTestBeansConfigPersistence;
import com.app.data.model.CarData;
import com.app.model.Color;
import com.app.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfigPersistence.class)
@TestPropertySource("classpath:application-test.properties")
public class CarDataValidatorTest {

    @Autowired
    private Validator<CarData> validator;

    static Stream<Arguments> carValidationData() {
        return Stream.of(
                Arguments.of(
                        new CarData(
                                "MAZDa",
                                "C",
                                200,
                                Color.BLACK,
                                BigDecimal.TEN,
                                List.of("A", "B")
                        ),
                        Map.of("make", " does not match regex: MAZDa")
                ),
                Arguments.of(
                        new CarData(
                                "MAZDA",
                                "C1",
                                200,
                                Color.BLACK,
                                BigDecimal.TEN,
                                List.of("A", "B")
                        ),
                        Map.of("model", " does not match regex: C1")
                ),
                Arguments.of(
                        new CarData(
                                "MAZDA",
                                "C",
                                -200,
                                Color.BLACK,
                                BigDecimal.TEN,
                                List.of("A", "B")
                        ),
                        Map.of("speed", " must be positive: -200")
                ),
                Arguments.of(
                        new CarData(
                                "MAZDA",
                                "C",
                                200,
                                Color.BLACK,
                                BigDecimal.valueOf(-10),
                                List.of("A", "B")
                        ),
                        Map.of("price", " must be positive: -10")
                ),
                Arguments.of(
                        new CarData(
                                "MAZDA",
                                "C",
                                200,
                                Color.BLACK,
                                BigDecimal.TEN,
                                List.of("Aa", "Bb")
                        ),
                        Map.of("equipment", " not all items match regex: [Aa, Bb]")
                ),
                Arguments.of(
                        new CarData(
                                "MAZDa",
                                "C1",
                                200,
                                Color.BLACK,
                                BigDecimal.TEN,
                                List.of("A", "B")
                        ),
                        Map.of(
                                "make", " does not match regex: MAZDa",
                                "model", " does not match regex: C1"
                        )
                ),
                Arguments.of(
                        new CarData(
                                null,
                                "C",
                                200,
                                Color.BLACK,
                                BigDecimal.TEN,
                                List.of("A", "B")
                        ),
                        Map.of("make", " does not match regex: null")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("carValidationData")
    @DisplayName("when car data validation is not successful")
    void test1(CarData cardata, Map<String, String> expectedErrors) {
        assertThat(validator.validate(cardata)).isEqualTo(expectedErrors);
    }

    @Test
    @DisplayName("when car data validation is successful")
    void test2() {
        var carData = new CarData(
                "MAZDA",
                "C",
                200,
                Color.BLACK,
                BigDecimal.TEN,
                List.of("A", "B")
        );
        assertThat(Validator.validate(carData, validator)).isTrue();
    }
}
