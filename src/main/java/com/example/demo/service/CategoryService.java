package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category find(int id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public Category save(CategoryDto categoryDto){
        if(categoryDto.getParentId()==0){
            Category result= CategoryMapper.toCategory(categoryDto,null);
            categoryRepository.save(result);
            return result;
        }else{
            Category category=find(categoryDto.getParentId());
            Category result=  CategoryMapper.toCategory(categoryDto,category);
            categoryRepository.save(result);
            return result;
        }

    }



}
