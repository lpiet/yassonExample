package com.transtrend.yassonExample;


import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TestDeserializer implements JsonbDeserializer<Map<Long, TestObject>> {
    private static final Jsonb JSONB = JsonbBuilder.create();

    @Override
    public Map<Long, TestObject> deserialize(
            JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        var result = new HashMap<Long, TestObject>();
        jsonParser.getObjectStream().forEach(jsonValue
                -> result.put(Long.valueOf(jsonValue.getKey()),
                JSONB.fromJson(jsonValue.getValue().toString(), TestObject.class)));
        return result;
    }
}
