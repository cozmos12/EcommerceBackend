package com.example.demo;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
@SpringBootTest

public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        categoryService=new CategoryService(categoryRepository);

    }
    @Test
    public void testFind(){
        Category category=new Category();
        category.setName("test");
        category.setId(1);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        Category result=categoryService.find(1);

        assertEquals("category Id",1,result.getId());
    }

    @Test
    public void testSave1() throws Exception {
        CategoryDto categoryDto=new CategoryDto();
        Category category1=new Category();
        category1.setId(1);
        category1.setName("test");
        category1.setCategory(null);
        categoryDto.setId(2);
        categoryDto.setParentId(1);
        categoryDto.setName("test2");
       Category category = CategoryMapper.toCategory(categoryDto,category1);
        when(categoryRepository.save(category)).thenReturn(category);
        Category result=categoryService.save(categoryDto);
        assertEquals("save category",2,result.getId());
    }

    @Test
    public void testSave2() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setParentId(0);
        categoryDto.setId(1);
        categoryDto.setName("test");
       Category category= CategoryMapper.toCategory(categoryDto,null);
        Category result=categoryService.save(categoryDto);
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals("save category",1,result.getId());

    }
}
