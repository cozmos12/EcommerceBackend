package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/category")
public class CategoryController {

    private  final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/findAll")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/find/{id}")
    public Category findCategory( @PathVariable  int id){
        return categoryService.find(id);
    }

    @PostMapping("/save")
    public Category save(@RequestBody CategoryDto categoryDto){
        return categoryService.save(categoryDto);
    }
}
