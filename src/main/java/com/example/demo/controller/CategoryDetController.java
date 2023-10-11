package com.example.demo.controller;

import com.example.demo.dto.CategoryDetDto;
import com.example.demo.entity.CategoryDetails;
import com.example.demo.service.CategoryDetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoryDet")
public class CategoryDetController {

    private final CategoryDetService categoryDetService;

    public CategoryDetController(CategoryDetService categoryDetService) {
        this.categoryDetService = categoryDetService;
    }


    @GetMapping("/findAll/{id}")
    public List<CategoryDetails> findAll(@PathVariable int id){
        return categoryDetService.findAll(id);
    }

    @PostMapping("/save")
    public List<CategoryDetails> save(@RequestBody List<String> categoryDetails, int categoryId){
        return categoryDetService.save(categoryDetails,categoryId);
    }
}
