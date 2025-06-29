package com.app.service.impl;

import com.app.model.Car;
import com.app.model.Color;
import com.app.service.CarService;
import com.app.util.MinMax;
import com.app.util.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
     * Returns a list of all cars that match the given predicate.
     *
     * @param criterion the condition used to filter cars
     * @return a list of cars that satisfy the provided criterion
     */
    @Override
    public List<Car> findAllBy(Predicate<Car> criterion) {
        return cars.stream().filter(criterion).toList();
    }

    /**
     * Groups all cars using the provided classification function.
     *
     * @param carFunction the function used to classify cars into groups; must not be null
     * @param <T>         the type of the key returned by the classification function
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
     * @param <T>        the type of the key returned by the classification function
     * @return a map where each key is a classification result and the value is the number of cars in that group
     */
    @Override
    public <T> Map<T, Long> countBy(Function<Car, T> classifier) {
        return cars
                .stream()
                .collect(Collectors.groupingBy(classifier, Collectors.counting()));
    }

    /**
     * Groups a collection of {@link Car} objects by a specified criterion and determines
     * the minimum and maximum {@link Car} in each group using the provided comparator.
     *
     * @param <T>              the type of the grouping key (e.g. make, color, etc.)
     * @param groupingFunction a function that extracts the grouping key from a {@link Car}; must not be null
     * @param carComparator    a comparator used to find the minimum and maximum cars within each group; must not be null
     * @return a map where each key is a group and the value is a {@link MinMax} containing the minimum and maximum car
     * @throws IllegalArgumentException if groupingFunction or carComparator is null
     */
    @Override
    public <T> Map<T, MinMax<Car>> groupAndFindMinMaxByCriteria(Function<Car, T> groupingFunction, Comparator<Car> carComparator) {
        if (groupingFunction == null) {
            throw new IllegalArgumentException("Grouping function is null");
        }
        if (carComparator == null) {
            throw new IllegalArgumentException("Comparator function is null");
        }
        return cars
                .stream()
                .collect(Collectors.groupingBy(
                        groupingFunction,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                groupedCars -> {
                                    var minCar = groupedCars
                                            .stream()
                                            .min(carComparator)
                                            .orElseThrow();
                                    var maxCar = groupedCars
                                            .stream()
                                            .max(carComparator)
                                            .orElseThrow();
                                    return new MinMax<>(minCar, maxCar);
                                })
                ));
    }

    /**
     *
     * @param groupingFunction
     * @param minMaxGroupingFunction
     * @param minMaxComparator
     * @return
     * @param <T>
     * @param <U>
     */
    /**
     * Groups a collection of {@link Car} objects using a primary grouping criterion, and for each group,
     * determines the subgroups (based on a secondary criterion) with the minimum and maximum keys, using
     * a provided comparator. Returns the corresponding lists of cars for those min and max subgroups.
     *
     * @param <T>                    the type of the grouping key (e.g. make, color, etc.)
     * @param <U>                    the type of the secondary key used for min/max evaluation (e.g., price)
     * @param groupingFunction       a function that extracts the grouping key from a {@link Car}; must not be null
     * @param minMaxGroupingFunction a function used to create subgroups within each primary group; must not be null
     * @param minMaxComparator       a comparator used to find the minimum and maximum keys among the subgroups; must not be null
     * @return a map where each key represents a group of cars, and the value is a {@link MinMax}
     * containing the lists of cars from the min and max subgroups
     * @throws java.util.NoSuchElementException if a group is unexpectedly empty
     */
    @Override
    public <T, U> Map<T, MinMax<List<Car>>> groupAndFindMinMaxByCriteria(
            Function<Car, T> groupingFunction, Function<Car, U> minMaxGroupingFunction, Comparator<U> minMaxComparator) {
        if (groupingFunction == null) {
            throw new IllegalArgumentException("Grouping function is null");
        }
        if (minMaxGroupingFunction == null) {
            throw new IllegalArgumentException("Min max grouping function is null");
        }
        if (minMaxComparator == null) {
            throw new IllegalArgumentException("Comparator function is null");
        }

        return cars
                .stream()
                .collect(Collectors.groupingBy(
                        groupingFunction,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(minMaxGroupingFunction),
                                groupedWithMinMaxFnCars -> {
                                    var minKey = groupedWithMinMaxFnCars
                                            .keySet()
                                            .stream()
                                            .min(minMaxComparator)
                                            .orElseThrow();
                                    var maxKey = groupedWithMinMaxFnCars
                                            .keySet()
                                            .stream()
                                            .max(minMaxComparator)
                                            .orElseThrow();
                                    var minKeyValue = groupedWithMinMaxFnCars.get(minKey);
                                    var maxKeyValue = groupedWithMinMaxFnCars.get(maxKey);
                                    return new MinMax<>(minKeyValue, maxKeyValue);
                                }
                        )
                ));
    }

    /**
     * Calculates simple descriptive statistics—minimum, maximum and, where possible, average—
     * for the values obtained from each {@link Car} in the current collection using the supplied
     * keyExtractor.
     *
     * @param <T>          the type produced by the extractor; must be comparable and
     *                     optionally numeric for the average to be computed
     * @param keyExtractor a function that maps a {@link Car} to a comparable key; must not be null
     * @return a {@link Statistics} instance whose min and max contain the
     *         boundary values, and whose avg}contains the average for numeric keys
     *         or null otherwise
     * @throws IllegalArgumentException if keyExtractor is null
     */
    @Override
    public <T extends Comparable<T>> Statistics<T> getStatistics(Function<Car, T> keyExtractor) {
        if (keyExtractor == null) {
            throw new IllegalArgumentException("KeyExtractor is null");
        }

        T min = cars
                .stream()
                .map(keyExtractor)
                .min(Comparator.naturalOrder())
                .orElse(null);

        T max = cars
                .stream()
                .map(keyExtractor)
                .max(Comparator.naturalOrder())
                .orElse(null);

        var bigDecimals = cars
                .stream()
                .map(keyExtractor)
                .filter(val -> val instanceof Number)
                .map(val -> {
                    if (val instanceof BigDecimal bigDecimal) {
                        return bigDecimal;
                    }
                    return new BigDecimal(val.toString());
                })
                .toList();

        BigDecimal avg = null;
        if (!bigDecimals.isEmpty()) {
            avg = bigDecimals
                    .stream()
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(bigDecimals.size()), 0, RoundingMode.HALF_UP);
        }

        return new Statistics<>(min, max, avg);
    }

}
