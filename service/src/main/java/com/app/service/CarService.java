package com.app.service;

import com.app.model.Car;
import com.app.util.MinMax;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CarService {
    List<Car> sort(Comparator<Car> carComparator);

    List<Car> findAllBySpeedBetween(int speedMin, int speedMax);

    List<Car> findAllBy(Predicate<Car> criterion);

    <T> Map<T, List<Car>> groupBy(Function<Car, T> carFunction);

    <T> Map<T, Long> countBy(Function<Car, T> classifier);

    <T> Map<T, MinMax<Car>> groupAndFindMinMaxByCriteria(Function<Car, T> groupingFunction, Comparator<Car> carComparator);

    <T, U> Map<T, MinMax<List<Car>>> groupAndFindMinMaxByCriteria(
            Function<Car, T> groupingFunction, Function<Car, U> minMaxGroupingFunction, Comparator<U> minMaxComparator);
}
