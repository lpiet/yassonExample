package com.transtrend.yassonExample;

import java.util.HashMap;
import java.util.Map;

public class TestGeneric<T> {
    private Map<String, HashMap<Long,T>> map = new HashMap<>();

    public TestGeneric() {
    }

    public Map<String, HashMap<Long, T>> getMap() {
        return map;
    }

    public void setMap(Map<String, HashMap<Long, T>> map) {
        this.map = map;
    }
}
