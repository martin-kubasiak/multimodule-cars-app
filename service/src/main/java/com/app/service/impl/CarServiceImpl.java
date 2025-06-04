package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final List<Car> cars;

    /**
     * Sorts the list of cars using the provided {@link Comparator}.
     *
     * @param carComparator the comparator/criterion to determine the order of the cars.
     *                      Must not be null.
     * @return a new list of cars sorted according to the specified comparator.
     * @throws IllegalArgumentException if carComparator is null
     */

    @Override
    public List<Car> sort(Comparator<Car> carComparator) {
        if (carComparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }
        return cars.stream().sorted(carComparator).toList();
    }

    /**
     * @param speedMin
     * @param speedMax
     * @return
     */
    @Override
    public List<Car> findAllBySpeedBetween(int speedMin, int speedMax) {
        if (speedMin > speedMax) {
            throw new IllegalArgumentException("Speed range is not correct");
        }
        return cars.stream().filter(car -> car.hasSpeedBetween(speedMin, speedMax)).toList();
    }
}
