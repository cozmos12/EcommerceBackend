package com.example.demo.elasticSearch.repository;

import com.example.demo.elasticSearch.entitiy.Details;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticDetailsRepository extends ElasticsearchRepository<Details,Integer> {
    List<Details> findByValueLike(String value);

}
