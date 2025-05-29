package com.app.model;

import java.util.Comparator;
import java.util.function.Function;

public interface Comparators {

    Comparator<Car> byMakeComparator = generalComparator(car -> car.make, false);
    Comparator<Car> byMakeComparatorDesc = generalComparator(car -> car.make, true);
    Comparator<Car> byPriceComparator = generalComparator(car -> car.price, false);
    Comparator<Car> byPriceComparatorDesc = generalComparator(car -> car.price, true);
    Comparator<Car> bySpeedComparator = generalComparator(car -> car.speed, false);
    Comparator<Car> getBySpeedComparatorComparatorDesc = generalComparator(car -> car.speed, true);

    private static <T, U extends Comparable<U>> Comparator<T> generalComparator(
            Function<T, U> keyExtractor, boolean descending) {
        return descending ? Comparator.comparing(keyExtractor, Comparator.reverseOrder()) :
                Comparator.comparing(keyExtractor);
    }
}
