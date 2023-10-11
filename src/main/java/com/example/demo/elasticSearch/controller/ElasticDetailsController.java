package com.example.demo.elasticSearch.controller;

import com.example.demo.elasticSearch.entitiy.Details;
import com.example.demo.elasticSearch.service.ElasticDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elastic")
public class ElasticDetailsController {
    private final ElasticDetailsService elasticDetailsService;

    public ElasticDetailsController(ElasticDetailsService elasticDetailsService) {
        this.elasticDetailsService = elasticDetailsService;
    }



    @GetMapping("/find/{value}")
    public List<Integer> getDetails(@PathVariable String value){
        return elasticDetailsService.getDetails(value);
    }





}
