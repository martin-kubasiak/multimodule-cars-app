package com.app;

import com.app.config.AppBeansConfigPersistence;
import com.app.data.json.converter.impl.GsonConverter;
import com.app.data.json.deserializer.impl.CarsDataJsonDeserializer;
import com.app.data.model.CarsCollection;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppBeansConfigPersistence.class);
        var filename = "cars.json";
        var carsDataJsonDeserializer =
                context.getBean("carsDataJsonDeserializer", CarsDataJsonDeserializer.class);
        carsDataJsonDeserializer.fromJson(filename).cars().forEach(System.out::println);

    }
}
