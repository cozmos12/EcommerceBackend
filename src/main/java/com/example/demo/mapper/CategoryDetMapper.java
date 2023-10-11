package com.example.demo.mapper;

import com.example.demo.dto.CategoryDetDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.CategoryDetails;

public class CategoryDetMapper {

    public static CategoryDetails toCategoryDetails(CategoryDetDto categoryDetDto, Category category) {
        CategoryDetails entity=new CategoryDetails();
        entity.setName(categoryDetDto.getName());
        entity.setCategory(category);
        return entity;
    }

}
