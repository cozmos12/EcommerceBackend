package com.example.demo.util.converter;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListToMap {

    public Map<String, String> convert(List<String> categoryNames,List<String>productValues){
        Map<String, String> details = new HashMap<String, String>();
        for(int i=0;i<categoryNames.size();i++){
            details.put(categoryNames.get(i),productValues.get(i));
        }

        return details;
    }



}
