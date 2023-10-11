package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product, Category category){
        ProductDto entity=new ProductDto();
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setCategoryId(category.getId());
        return entity;
    }

    public static Product toProduct(ProductDto productDto,Category category ,int price){
        Product entity=new Product();
        entity.setCategory(category);
        entity.setPrice(price);
        entity.setName(productDto.getName());
        return entity;
    }



}
