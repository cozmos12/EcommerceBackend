package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDetDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.util.converter.JsonToList;
import com.example.demo.util.converter.JsonToString;
import com.example.demo.util.converter.ProductSaveRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/findAll")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/findAllByCategoryId/{id}")
    public List<Product> getAllCategories(@PathVariable int id){
        return productService.findByCategory(id);
    }

    @GetMapping("/ProductComparison/{categoryId}/{productId1}/{productId2}")
    public Map<String, List<String>> productComparison(@PathVariable int categoryId, @PathVariable int productId1, @PathVariable int productId2){
        return productService.productComparison(categoryId, productId1, productId2);
    }

    @PostMapping("/save/{categoryId}")
    public Product save(@RequestBody ProductSaveRequest productSaveRequest,@PathVariable int categoryId){
        return productService.save(productSaveRequest.getProductDto(),productSaveRequest.getJsonToList().getValues(),categoryId);
    }

    @PatchMapping("/update")
    public Product update(@RequestBody ProductDto product){
        return productService.update(product);
    }
}
