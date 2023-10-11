package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.CategoryDetRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.comporison.ProductComparison;
import com.example.demo.util.converter.ListToMap;
import com.example.demo.util.stoc.StockTracking;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CategoryDetRepository categoryDetRepository;

    private final ProductComparison productComparison;

    private final ProductDetService productDetService;
    private final ListToMap listToMap;
    private final StockTracking stockTracking;


    public ProductService(ProductRepository productRepository, CategoryService categoryService, CategoryDetRepository categoryDetRepository, ProductComparison productComparison, ProductDetService productDetService, ListToMap listToMap, StockTracking stockTracking ) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryDetRepository = categoryDetRepository;
        this.productComparison = productComparison;
        this.productDetService = productDetService;
        this.listToMap = listToMap;
        this.stockTracking = stockTracking;

    }



    public List<Product> findAll() {
            return productRepository.findAll();
    }

    public Product find(int id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public List<Product> findByCategory(int id){
        return productRepository.findByCategory_Id(id);
    }


    public Product save(ProductDto productDto,List<String> values,int categoryId) {
        boolean hasDuplicate = productRepository.existsByFieldValues(productDto.getName(), productDto.getPrice(), productDto.getDescription());
        List<String> categoryNames=categoryDetRepository.findNamesByCategoryId(categoryId);

        Map<String,String> details=listToMap.convert(categoryNames,values);

        if(stockTracking.control(categoryId,details)){
         return update(productDto);
        }else{
            Category category= categoryService.find(productDto.getCategoryId());
            if(category!=null){
                Product product=  ProductMapper.toProduct(productDto,category,productDto.getPrice());
                productRepository.save(product);
                productDetService.save(values,3,product.getId());
                return product;
            }else{
                return null;
            }
        }
    }

    public Product update(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            List<Field> fields = Arrays.asList(productDto.getClass().getDeclaredFields());
            fields.forEach(field -> {
                field.setAccessible(true);
                try {
                    Object value = field.get(productDto);
                    if (value != null) {
                        Field productField = existingProduct.getClass().getDeclaredField(field.getName());
                        productField.setAccessible(true);
                        productField.set(existingProduct, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return productRepository.save(existingProduct);
        }
        return null;
    }

    public Map<String, List<String>> productComparison(int categoryId, int productId1, int productId2){

      return   productComparison.compareComputerDetails(categoryId,productId1,productId2);

    }














}
