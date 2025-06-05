package com.app.service.impl;

import com.app.model.Car;
import com.app.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final List<Car> cars;

    /**
     * Sorts the list of cars using the provided {@link Comparator<Car>}.
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
     * Retrieves all cars with a speed within the specified range (inclusive).
     *
     * @param speedMin the minimum speed (inclusive)
     * @param speedMax the maximum speed (inclusive)
     * @return a list of cars whose speed is between speedMin and speedMax, inclusive
     * @throws IllegalArgumentException if speedMin is greater than speedMax
     */
    @Override
    public List<Car> findAllBySpeedBetween(int speedMin, int speedMax) {
        if (speedMin > speedMax) {
            throw new IllegalArgumentException("Speed range is not correct");
        }
        return cars.stream().filter(car -> car.hasSpeedBetween(speedMin, speedMax)).toList();
    }

    /**
     * Groups all cars using the provided classification function.
     *
     * @param carFunction the function used to classify cars into groups; must not be null
     * @param <T> the type of the key returned by the classification function
     * @return a map where each key is a classification result and the value is a list of cars in that group
     */
    @Override
    public <T> Map<T, List<Car>> groupBy(Function<Car, T> carFunction) {
        return cars
                .stream()
                .collect(Collectors.groupingBy(carFunction));
    }

    /**
     * Counts the number of cars in each group defined by the provided classification function.
     *
     * @param classifier the function used to classify cars into groups; must not be null
     * @param <T> the type of the key returned by the classification function
     * @return a map where each key is a classification result and the value is the number of cars in that group
     */
    @Override
    public <T> Map<T, Long> countBy(Function<Car, T> classifier) {
        return cars
                .stream()
                .collect(Collectors.groupingBy(classifier, Collectors.counting()));
    }

}
