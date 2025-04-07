package com.app.config;

import com.app.data.json.converter.JsonConverter;
import com.app.data.json.converter.impl.GsonConverter;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.json.deserializer.impl.CarsCollectionJsonDeserializer;
import com.app.data.model.CarData;
import com.app.data.model.CarsCollection;
import com.app.validation.Validator;
import com.app.validation.impl.CarDataValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;


public class AppTestBeansConfigPersistence {
    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public JsonConverter<CarsCollection> jsonConverter(Gson gson) {
        return new GsonConverter<>(gson);
    }

    @Bean
    public JsonDeserializer<CarsCollection> jsonDeserializer(JsonConverter<CarsCollection> jsonConverter) {
        return new CarsCollectionJsonDeserializer(jsonConverter);
    }

    @Bean
    public Validator<CarData> validator() {
        return new CarDataValidator();
    }
}
