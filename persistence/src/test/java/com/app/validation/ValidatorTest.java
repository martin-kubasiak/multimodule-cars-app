package com.app.validation;

import com.app.config.AppTestBeansConfigPersistence;
import com.app.data.model.CarData;
import com.app.model.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfigPersistence.class)
@TestPropertySource("classpath:application-test.properties")
public class ValidatorTest {

    @Autowired
    private Validator<CarData> validator;

    @Test
    @DisplayName("when validation is successful")
    void test1() {
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

    @Test
    @DisplayName("when validation is not successful")
    void test2() {
        var carData = new CarData(
                "MAZDa",
                "C",
                200,
                Color.BLACK,
                BigDecimal.TEN,
                List.of("A", "B")
        );
        assertThat(Validator.validate(carData, validator)).isFalse();
    }
}
