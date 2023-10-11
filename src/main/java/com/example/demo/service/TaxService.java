package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.util.taxUtil.*;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final CategoryService categoryService;
    private TaxInterface taxInterface;

    public TaxService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public int taxCalculator(ProductDto productDto){
        Category parent;
        parent=categoryService.find(productDto.getId());
         while (parent.getCategory()!=null){
              parent= categoryService.find(parent.getCategory().getId());
         }
         System.out.println(parent.getName());


        if(parent.getName().equals("Elektronik")){
            KdvTax taxInterface1 = new KdvTax(new OtvTax(new TrtTax(new BaseTax(productDto.getPrice()))));
            taxInterface= taxInterface1;
        }
        if(parent.getName().equals("Kitap")){
         taxInterface= new KdvTax(new OtvTax(new BaseTax(productDto.getPrice())));

        }
        if(parent.getName().equals("Temizlik")){
           taxInterface= new KdvTax(new BaseTax(productDto.getPrice()));

        }
        System.out.println(taxInterface.calculate());
         return (int) taxInterface.calculate();

     }

}
