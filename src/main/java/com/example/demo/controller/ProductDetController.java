package com.example.demo.controller;

import com.example.demo.dto.ProductDetDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductsDetails;
import com.example.demo.service.ProductDetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productDet")
public class ProductDetController {
    private final ProductDetService productDetService;

    public ProductDetController(ProductDetService productDetService) {
        this.productDetService = productDetService;
    }

    @GetMapping("/findAll/{id}")
    public List<ProductsDetails> findAll(@PathVariable int id){
        return productDetService.findAll(id);
    }

    @PostMapping("/save/{categoryId}/{productId}")
    public List<ProductsDetails> save(@RequestBody List<String> details,@PathVariable int categoryId,@PathVariable int productId){
        return productDetService.save(details, categoryId, productId);
    }

    @GetMapping("/find/{value}")
    public List<Optional<Product>> getProducts(@PathVariable String value){
       String str= String.valueOf(value);
        return productDetService.findByProduct(str);
    }


}
