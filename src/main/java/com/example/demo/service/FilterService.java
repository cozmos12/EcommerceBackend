package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryDetRepository;
import com.fasterxml.jackson.databind.JsonNode;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class FilterService {

    @PersistenceContext
    EntityManager entityManager;

    private JsonNode data;



    @Transactional
    public List<Product> findAll(final int categoryId, final Map<String, String> filter) {
        String queryStr = "SELECT p.id FROM Product p " +
                " INNER JOIN products_details pd ON pd.product_id = p.id " +
                " INNER JOIN category_details cd ON  cd.category_id = " + categoryId;

        List<String> list = new ArrayList<>();

        for (final Map.Entry<String, String> stringStringEntry : filter.entrySet()) {
            final String name = stringStringEntry.getKey();
            final String value = stringStringEntry.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            list.add("(cd.name = '" + name + "' AND pd.value='" + value + "')");
        }

        String where = String.join(" OR", list);
        queryStr = queryStr + " WHERE " + where + " GROUP BY p.id HAVING COUNT(1) = " + filter.size();
        queryStr = " WITH FILTERED_PRODUCTS AS (" + queryStr + ")" +
                " SELECT p.* FROM FILTERED_PRODUCTS fp" +
                " INNER JOIN product p ON p.id = fp.id";
        jakarta.persistence.Query query = entityManager.createNativeQuery(queryStr);
        return query.getResultList();
    }

}
