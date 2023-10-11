package com.example.demo.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryDto categoryDto,Category category){
        Category entity=new Category();
        entity.setId(categoryDto.getId());
        entity.setName(categoryDto.getName());
        entity.setCategory(category);
        return entity;
    }

    public static CategoryDto toDto(Category category){
        CategoryDto entity=new CategoryDto();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setParentId(category.getId());
        return entity;
    }

}
