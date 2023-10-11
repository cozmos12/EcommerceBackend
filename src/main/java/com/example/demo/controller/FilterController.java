package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.FilterService;
import com.example.demo.util.converter.JsonToString;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {
    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;

    }

    @PostMapping("/filter")
    public List<Product> filter(@RequestParam int id, @RequestBody JsonToString jsonToString){

        return filterService.findAll(id, jsonToString.getFilterMap());
    }
}

