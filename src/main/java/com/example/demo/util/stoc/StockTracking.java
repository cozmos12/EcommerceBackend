package com.example.demo.util.stoc;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.service.FilterService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StockTracking {

    private final FilterService filterService;

    public StockTracking(FilterService filterService) {
        this.filterService = filterService;
    }

    public Boolean control(int categoryId, Map<String,String> details) {
        List<Product> result=filterService.findAll(categoryId,details);
        if(result.size()>0){
            return true;
        }else{
            return false;
        }

    }

}
