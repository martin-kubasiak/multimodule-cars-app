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

    CarData CAR_DATA_TOYOTA = new CarData(
            "TOYOTA",
            "A",
            220,
            Color.BLUE,
            BigDecimal.TWO,
            List.of("A", "B", "C")
    );
    Car CAR_TOYOTA = CAR_DATA_TOYOTA.toCar();

    CarData CAR_DATA_BMW = new CarData(
            "BMW",
            "X",
            250,
            Color.GREEN,
            BigDecimal.TEN,
            List.of("B", "C")
    );
    Car CAR_BMW = CAR_DATA_BMW.toCar();
}
