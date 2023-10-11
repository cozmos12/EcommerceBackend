package com.example.demo.util.converter;

import org.springframework.stereotype.Component;

import java.util.Map;
public class JsonToString {
    private Map<String, String> filterMap;

    public Map<String, String> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(Map<String, String> filterMap) {
        this.filterMap = filterMap;
    }
}
