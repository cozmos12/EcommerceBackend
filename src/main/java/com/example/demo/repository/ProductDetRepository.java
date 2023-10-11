package com.example.demo.repository;

import com.example.demo.entity.CategoryDetails;
import com.example.demo.entity.ProductsDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDetRepository  extends JpaRepository<ProductsDetails,Integer> {
    List<ProductsDetails> findByProduct_Id(int id);
    @Query("SELECT pd.value FROM ProductsDetails pd WHERE pd.product.id = :productId")
    List<String> findValuesByProductId(int productId);

}
