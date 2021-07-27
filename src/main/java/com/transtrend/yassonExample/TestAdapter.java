package com.transtrend.yassonExample;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.adapter.JsonbAdapter;
import java.util.HashMap;
import java.util.Map;

public class TestAdapter implements JsonbAdapter<TestGeneric<TestObject>, JsonObject> {
    Jsonb JSON_BUILDER =
            JsonbBuilder.create(new JsonbConfig().withDeserializers(new TestDeserializer()));

    @Override
    public JsonObject adaptToJson(TestGeneric<TestObject> data) throws Exception {
        System.out.println((JsonObjectBuilder)  data.getMap());
        return Json.createObjectBuilder()

                .add("map", (JsonObjectBuilder) data.getMap())
                .build();
    }

    @Override
    public TestGeneric<TestObject> adaptFromJson(JsonObject jsonObject) throws Exception {
        TestGeneric<TestObject> data = new TestGeneric<>();
        JsonObject map = jsonObject.getJsonObject("map");
        System.out.println(map);
        Map<String, HashMap<Long, TestObject>> dataMap = new HashMap<>();
        for (String id : map.keySet()) {
            System.out.println("id: "+ id + " Value " + JSON_BUILDER.toJson(map.get(id)));

            dataMap.put(id,
                    JSON_BUILDER.fromJson(JSON_BUILDER.toJson(map.get(id)),
                            new HashMap<Long, TestObject>() {}.getClass().getGenericSuperclass()));
        }

        data.setMap(dataMap);

        return data;
    }
}
