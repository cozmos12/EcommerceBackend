package com.example.demo.util.comporison;
import com.example.demo.repository.CategoryDetRepository;
import com.example.demo.repository.ProductDetRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductComparison {

    private final CategoryDetRepository categoryDetRepository;

    private final ProductDetRepository productDetRepository;

    public ProductComparison(CategoryDetRepository categoryDetRepository, ProductDetRepository productDetRepository) {
        this.categoryDetRepository = categoryDetRepository;
        this.productDetRepository = productDetRepository;
    }


    public Map<String, List<String>> compareComputerDetails(int categoryId,int productId1,int productId2) {
            Map<String, List<String>> comparisonResult = new HashMap<>();
            List<String> categoryName= categoryDetRepository.findNamesByCategoryId(categoryId);
            List<String> productValue1=productDetRepository.findValuesByProductId(productId1);
            List<String> productValue2=productDetRepository.findValuesByProductId(productId2);

            for (String detail : productValue1) {
                if (!comparisonResult.containsKey(detail)) {
                    comparisonResult.put(detail, new ArrayList<>());
                }
                comparisonResult.get(detail).add("Product1");
            }

            for (String detail : productValue2) {
                if (!comparisonResult.containsKey(detail)) {
                    comparisonResult.put(detail, new ArrayList<>());
                }
                comparisonResult.get(detail).add("Product2");
            }

            return comparisonResult;
        }


}
