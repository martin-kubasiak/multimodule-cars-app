package com.app.data.json.deserializer.impl;

import com.app.data.json.converter.JsonConverter;
import com.app.data.json.deserializer.JsonDeserializer;
import com.app.data.json.deserializer.generic.AbstractJsonDeserializer;
import com.app.data.model.CarsCollection;
import org.springframework.stereotype.Component;

@Component
public class CarsDataJsonDeserializer extends AbstractJsonDeserializer<CarsCollection> implements JsonDeserializer<CarsCollection> {
    public CarsDataJsonDeserializer(JsonConverter<CarsCollection> converter) {
        super(converter);
    }
}
