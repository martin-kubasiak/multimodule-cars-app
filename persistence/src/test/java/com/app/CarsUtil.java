package com.app;

import com.app.data.model.CarData;
import com.app.model.Color;

import java.math.BigDecimal;
import java.util.List;

public interface CarsUtil {
    CarData CAR_DATA_MAZDA = new CarData(
            "MAZDA",
            "C",
            200,
            Color.BLACK,
            BigDecimal.TEN,
            List.of("A", "B")
    );
    CarData CAR_DATA_TOYOTA = new CarData(
            "TOYOTA",
            "A",
            220,
            Color.BLUE,
            BigDecimal.TWO,
            List.of("A", "B", "C")
    );
    CarData CAR_DATA_BMW = new CarData(
            "BMW",
            "X",
            250,
            Color.GREEN,
            BigDecimal.TEN,
            List.of("B", "C")
    );
}
