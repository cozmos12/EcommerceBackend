package com.example.demo.util.converter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonToList {
    private List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
