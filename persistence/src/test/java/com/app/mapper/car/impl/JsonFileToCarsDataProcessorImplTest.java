package com.app.mapper.car.impl;

import com.app.config.AppTestBeansConfigPersistence;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.model.CarData;
import com.app.data.model.CarsCollection;
import com.app.mapper.car.FileToCarsDataProcessor;
import com.app.model.Color;
import com.app.validation.Validator;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = AppTestBeansConfigPersistence.class)
@TestPropertySource("classpath:application-test.properties")
public class JsonFileToCarsDataProcessorImplTest {
    @Autowired
    private Validator<CarData> carDataValidator;

    @Mock
    private JsonDeserializer<CarsCollection> carsCollectionJsonDeserializer;

    private FileToCarsDataProcessor fileToCarsDataProcessor;

    @BeforeEach
    void setUp() {
        fileToCarsDataProcessor
                = new JsonFileToCarsDataProcessorImpl(carsCollectionJsonDeserializer, carDataValidator);
    }

    @Test
    void test1() {
        Mockito
                .when(carsCollectionJsonDeserializer.fromJson(ArgumentMatchers.anyString()))
                .thenReturn(new CarsCollection(
                                List.of(
                                        new CarData(
                                                "MAZDA",
                                                "C",
                                                200,
                                                Color.BLACK,
                                                BigDecimal.TEN,
                                                List.of("A", "B")
                                        ),
                                        new CarData(
                                                "TOYOTA",
                                                "A",
                                                220,
                                                Color.BLUE,
                                                BigDecimal.TWO,
                                                List.of("A", "B", "C")
                                        )
                                )
                        )
                );
        Assertions
                .assertThat(carsCollectionJsonDeserializer.fromJson("cars.json").cars())
                .hasSize(2);
    }


}
