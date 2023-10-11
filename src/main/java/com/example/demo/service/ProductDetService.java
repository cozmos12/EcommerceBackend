package com.example.demo.service;

import com.example.demo.elasticSearch.service.ElasticDetailsService;
import com.example.demo.entity.CategoryDetails;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductsDetails;
import com.example.demo.repository.CategoryDetRepository;
import com.example.demo.repository.ProductDetRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetService {

private final ProductDetRepository productDetRepository;
private final ProductRepository productRepository;

private final CategoryDetRepository categoryDetRepository;

private final ElasticDetailsService elasticDetailsService;

    public ProductDetService(ProductDetRepository productDetRepository, ProductRepository productRepository, CategoryDetRepository categoryDetRepository, ElasticDetailsService elasticDetailsService) {
        this.productDetRepository = productDetRepository;
        this.productRepository = productRepository;
        this.categoryDetRepository = categoryDetRepository;
        this.elasticDetailsService = elasticDetailsService;
    }


    public List<ProductsDetails> findAll(int id) {
        return productDetRepository.findByProduct_Id(id);
    }

    public List<Optional<Product>> findByProduct(String value){
        List<Optional<Product>> result = new ArrayList<>();

      List<Integer> productId= elasticDetailsService.getDetails(value);

      for(Integer product : productId){
          result.add(productRepository.findById(product));
      }
      return result;
    }


    public List<ProductsDetails> save(List<String> values, int categoryId, int productId) {
        List<Integer> categoryDetailsIds = categoryDetRepository.findIdsByCategoryId(categoryId);
        List<ProductsDetails> productsDetailsList = new ArrayList<>();

        for (int i = 0; i < categoryDetailsIds.size(); i++) {
            String value = values.get(i);
            int categoryDetailsId = categoryDetailsIds.get(i);

            CategoryDetails categoryDetails = categoryDetRepository.getById(categoryDetailsId);

            ProductsDetails productsDetails = new ProductsDetails();
            productsDetails.setValue(value);
            productsDetails.setProduct(productRepository.findById(productId).get());
            productsDetails.setCategoryDetails(categoryDetails);




            productsDetailsList.add(productsDetails);
        }
        productDetRepository.saveAll(productsDetailsList);

        elasticDetailsService.save(values,productId,categoryId);

        return productsDetailsList;

    }




}
