package com.example.demo.service;

import com.example.demo.dto.CategoryDetDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.CategoryDetails;
import com.example.demo.mapper.CategoryDetMapper;
import com.example.demo.repository.CategoryDetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryDetService {
    private final CategoryDetRepository categoryDetRepository;
    private final CategoryService categoryService;


    public CategoryDetService(CategoryDetRepository categoryDetRepository, CategoryService categoryService) {
        this.categoryDetRepository = categoryDetRepository;
        this.categoryService = categoryService;
    }

    public List<CategoryDetails> findAll(int id){
        return categoryDetRepository.findByCategory_Id(id);
    }

    public List<CategoryDetails> save(List<String> catDetails, int categoryId){
        List<CategoryDetails> categoryDetailsList=new ArrayList<>();

        for(int i=0;i<catDetails.size();i++){

            String name=catDetails.get(i);
            CategoryDetails categoryDetails=new CategoryDetails();

            categoryDetails.setName(name);
            categoryDetails.setCategory(categoryService.find(categoryId));

            categoryDetailsList.add(categoryDetails);
        }
        return categoryDetRepository.saveAll(categoryDetailsList);


    }

    public CategoryDetails find(int id){
        Optional <CategoryDetails> categoryDetails = categoryDetRepository.findById(id);
        if(categoryDetails.isPresent()){
            return categoryDetails.get();
        }else{
            return null;
        }
    }

}
