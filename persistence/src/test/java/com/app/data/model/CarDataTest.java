package com.app.data.model;

import com.app.model.Car;
import com.app.model.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CarDataTest {
    @Test
    @DisplayName("when convertsion from CarData to Car is successful")
    void test1() {
        var carData = new CarData(
                "MAZDA",
                "CX-5",
                180,
                Color.GREEN,
                BigDecimal.TEN,
                List.of("X", "Y")
        );

        var expectedCar = new Car(
                "MAZDA",
                "CX-5",
                180,
                Color.GREEN,
                BigDecimal.TEN,
                List.of("X", "Y")
        );

        assertThat(carData.toCar()).isEqualTo(expectedCar);
    }
}

