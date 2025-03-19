package com.app;

import com.app.config.AppBeansConfigPersistence;
import com.app.data.json.deserializer.impl.CarsCollectionJsonDeserializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppBeansConfigPersistence.class);
        var filename = "cars.json";
        var carsDataJsonDeserializer =
                context.getBean("carsCollectionJsonDeserializer", CarsCollectionJsonDeserializer.class);
        carsDataJsonDeserializer.fromJson(filename).cars().forEach(System.out::println);

    }
}
