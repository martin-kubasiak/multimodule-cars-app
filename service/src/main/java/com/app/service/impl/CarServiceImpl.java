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

    @Override
    public List<Car> sort(Comparator<Car> carComparator) {
        return null;
    }
}
