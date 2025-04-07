package com.app.mapper.car.impl;

import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.model.CarData;
import com.app.mapper.car.FileToCarsDataProcessor;
import com.app.data.model.CarsCollection;
import com.app.model.Car;
import com.app.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonFileToCarsDataProcessorImpl implements FileToCarsDataProcessor {

    private final JsonDeserializer<CarsCollection> carsCollectionJsonDeserializer;
    private final Validator<CarData> carDataValidator;

    @Override
    public List<Car> mapData(String filename) {
        return null;
    }
}
