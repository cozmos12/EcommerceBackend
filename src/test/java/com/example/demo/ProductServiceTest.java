package com.example.demo;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.CategoryDetRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductDetService;
import com.example.demo.service.ProductService;
import com.example.demo.util.comporison.ProductComparison;
import com.example.demo.util.converter.ListToMap;
import com.example.demo.util.stoc.StockTracking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private  ProductRepository productRepository;

    @InjectMocks
    private  CategoryService categoryService;

    @Mock
    private  CategoryDetRepository categoryDetRepository;

    @Mock
    private  ProductComparison productComparison;
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private  ProductDetService productDetService;

    @Mock
    private  ListToMap listToMap;

    @Mock
    private  StockTracking stockTracking;

    @InjectMocks
    ProductService productService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        productService=new ProductService(productRepository,categoryService,categoryDetRepository,productComparison,productDetService
        ,listToMap,stockTracking);
        categoryService=new CategoryService(categoryRepository);
    }

    @Test
    public void testFind(){
        Product product=new Product();
        product.setId(1);
        product.setName("test");
        product.setPrice(1000);
        product.setActive(true);
        product.setDataCreated(new Date());
        product.setDescription("test description");
        product.setImageUrl("http://");
        product.setCategory(null);
        product.setStocks(10);
        product.setLastUpdated(new Date());
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        Product result=productService.find(1);
        assertEquals("products found",1,result.getId());
    }

    @Test
    public void testSave(){
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

        List<String> values=new ArrayList<String>();

        ProductDto product=new ProductDto();
        product.setId(1);
        product.setName("test");
        product.setPrice(1000);
        product.setCategoryId(2);
        product.setActive(true);
        product.setDataCreated(new Date());
        product.setDescription("test description");
        product.setImageUrl("http://");
        product.setStocks(10);
        product.setLastUpdated(new Date());
        Product product1=ProductMapper.toProduct(product,category,1000);
        when(productRepository.save(product1)).thenReturn(product1);
        Product result2=productService.save(product,values,2);
        assertEquals("test save",1,result2.getId());
    }

}
