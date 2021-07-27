package com.transtrend.yassonExample.yassonExample;

import com.transtrend.yassonExample.TestAdapter;
import com.transtrend.yassonExample.TestDeserializer;
import com.transtrend.yassonExample.TestGeneric;
import com.transtrend.yassonExample.TestObject;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.util.HashMap;

public class TestObjectThis {

    private static final Jsonb JSON_BUILDER =
            JsonbBuilder.create(new JsonbConfig().withAdapters(new TestAdapter()));

    @Test
    public void createString(){
        TestObject a= new TestObject();
        TestGeneric<TestObject> b = new TestGeneric<>();
        a.setA("a");
        b.getMap().put("a1",new HashMap<>());
        b.getMap().get("a1").put(1L,a);
        System.out.println(JSON_BUILDER.toJson(b));

    }

    @Test
    public void deSerialize(){
        TestGeneric<TestObject> b = JSON_BUILDER.fromJson("{\"map\":{\"a1\":{\"1\":{\"a\":\"a\"}}}}",new TestGeneric<TestObject>(){}.getClass().getGenericSuperclass());
        for (Long a1 : b.getMap().get("a1").keySet()) {
            System.out.println(b.getMap().get("a1").get(a1).getA());
        }

    }
}
