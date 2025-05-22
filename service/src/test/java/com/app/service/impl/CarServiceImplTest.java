package com.app.service.impl;

import com.app.model.Car;

import java.util.List;

import static com.app.CarsUtil.*;


public class CarServiceImplTest {
    private static final List<Car> CARS = List.of(
            CAR_DATA_MAZDA.toCar(),
            CAR_DATA_TOYOTA.toCar(),
            CAR_DATA_BMW.toCar()
    );



}
