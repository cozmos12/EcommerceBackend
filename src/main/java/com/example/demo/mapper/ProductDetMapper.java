package com.example.demo.mapper;

import com.example.demo.dto.ProductDetDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.CategoryDetails;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductsDetails;

public class ProductDetMapper {

    public static ProductsDetails toProductDet(ProductDetDto productDetDto, CategoryDetails categoryDetails,Product product){
        ProductsDetails entity=new ProductsDetails();
        entity.setValue(productDetDto.getValue());
        entity.setProduct(product);
        entity.setCategoryDetails(categoryDetails);
        return entity;
    }
}
