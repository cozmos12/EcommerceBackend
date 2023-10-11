package com.example.demo.elasticSearch.service;

import com.example.demo.elasticSearch.entitiy.Details;
import com.example.demo.elasticSearch.repository.ElasticDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticDetailsService {

    private final ElasticDetailsRepository elasticsearchRepository;
    private final Details detail;

    public ElasticDetailsService(ElasticDetailsRepository elasticsearchRepository, Details detail) {
        this.elasticsearchRepository = elasticsearchRepository;
        this.detail = detail;
    }

    public Details get(){
        return (Details) elasticsearchRepository.findAll();
    }


    public List<Details> save(List<String> values ,int productId, int categoryId){
        List<Details> details=new ArrayList<>();

            detail.setValue(values);
            detail.setCategoryId(categoryId);
            detail.setProductId(productId);
            elasticsearchRepository.save(detail);
            details.add(detail);


        System.out.println(details);
        return details;
    }

    public List<Integer> getDetails(String value) {

        List<Details> details= elasticsearchRepository.findByValueLike(value);
        List<Integer>productId=new ArrayList<>();
        for(Details detail : details){
            productId.add(detail.getProductId());
        }
        return productId;

    }


}
