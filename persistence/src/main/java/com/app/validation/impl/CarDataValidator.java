package com.app.validation.impl;

import com.app.data.model.CarData;
import com.app.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class CarDataValidator implements Validator<CarData> {
    @Value("${validation.regex}")
    private String regex;
    @Override
    public Map<String, String> validate(CarData carData) {
        Map<String, String> errors = new HashMap<>();

        if (doesNotMatchRegex(carData.make(), regex)) {
            errors.put("make", " does not match regex: " + carData.make());
        }
        if (doesNotMatchRegex(carData.model(), regex)) {
            errors.put("model", " does not match regex: " + carData.model());
        }
        if (carData.equipment().stream().anyMatch(eq -> doesNotMatchRegex(eq, regex))) {
            errors.put("equipment", " not all items match regex: " + carData.equipment());
        }
        if (carData.speed() <= 0) {
            errors.put("speed", " must be positive: " + carData.speed());
        }
        if (carData.price().compareTo(BigDecimal.ZERO) <= 0) {
            errors.put("price", " must be positive: " + carData.price());
        }
        return errors;
    }

    private static boolean doesNotMatchRegex(String text, String regex) {
        return text == null || !text.matches(regex);
    }
}
