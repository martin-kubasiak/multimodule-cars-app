package com.app.data.json.deserializer.impl;

import com.app.config.AppTestBeansConfigPersistence;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.model.CarsCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfigPersistence.class)
public class CarsCollectionJsonDeserializerTest {

    @Autowired
    private JsonDeserializer<CarsCollection> jsonDeserializer;

    @Test
    @DisplayName("when data is deserialized correctly")
    void test1() {
        var path = Paths
                .get("src", "test", "resources", "cars-test.json")
                .toFile()
                .getAbsolutePath();
        var cars = jsonDeserializer.fromJson(path).cars();
        assertThat(cars).hasSize(3);
    }

    @Test
    @DisplayName("when data is not deserialized correctly, then exception is thrown")
    void test2() {
        var path = Paths
                .get("src", "test", "resources", "bad-json-test-file.json")
                .toFile()
                .getAbsolutePath();
        assertThatThrownBy(() -> jsonDeserializer.fromJson(path))
                .isInstanceOf(FileNotFoundException.class);
    }

}
