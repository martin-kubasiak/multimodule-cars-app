package com.app;

import com.app.data.model.CarData;
import com.app.model.Car;
import com.app.model.Color;

import java.math.BigDecimal;
import java.util.List;

public interface CarsUtil {
    CarData CAR_DATA_MAZDA = new CarData(
            "MAZDA",
            "C",
            200,
            Color.BLACK,
            BigDecimal.ONE,
            List.of("A", "B")
    );
    Car CAR_MAZDA = CAR_DATA_MAZDA.toCar();

    CarData CAR_DATA_TOYOTA_1 = new CarData(
            "TOYOTA",
            "A",
            220,
            Color.BLUE,
            BigDecimal.TWO,
            List.of("A", "B", "C")
    );
    Car CAR_TOYOTA_1 = CAR_DATA_TOYOTA_1.toCar();

    CarData CAR_DATA_TOYOTA_2 = new CarData(
            "TOYOTA",
            "AA",
            205,
            Color.WHITE,
            BigDecimal.TEN,
            List.of("D", "E")
    );
    Car CAR_TOYOTA_2 = CAR_DATA_TOYOTA_2.toCar();

    CarData CAR_DATA_TOYOTA_3 = new CarData(
            "TOYOTA",
            "AAT",
            205,
            Color.WHITE,
            BigDecimal.TEN,
            List.of("D", "E")
    );
    Car CAR_TOYOTA_3 = CAR_DATA_TOYOTA_3.toCar();

    CarData CAR_DATA_BMW_1 = new CarData(
            "BMW",
            "X",
            250,
            Color.GREEN,
            BigDecimal.TEN,
            List.of("B", "C")
    );
    Car CAR_BMW_1 = CAR_DATA_BMW_1.toCar();

    CarData CAR_DATA_BMW_2 = new CarData(
            "BMW",
            "XX",
            235,
            Color.BLACK,
            BigDecimal.ONE,
            List.of("C", "D")
    );
    Car CAR_BMW_2 = CAR_DATA_BMW_2.toCar();
}
