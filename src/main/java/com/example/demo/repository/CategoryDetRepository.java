package com.example.demo.repository;

import com.example.demo.entity.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryDetRepository extends JpaRepository<CategoryDetails,Integer> {
    List<CategoryDetails> findByCategory_Id(int id);
    @Query("SELECT cd.id FROM CategoryDetails cd WHERE cd.category.id = :categoryId")
    List<Integer> findIdsByCategoryId(int categoryId);



    @Query("SELECT cd.name FROM CategoryDetails cd WHERE cd.category.id = :categoryId")
    List<String> findNamesByCategoryId(int categoryId);


}
