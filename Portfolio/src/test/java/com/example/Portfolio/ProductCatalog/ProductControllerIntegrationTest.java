package com.example.Portfolio.ProductCatalog;

import com.example.Portfolio.ProductCatalog.Models.Product;
import com.example.Portfolio.ProductCatalog.Services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetAllProductsByCategory() throws Exception {

        String category = "men";

        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        Page<Product> dummyProductPage = new PageImpl<>(Arrays.asList(
                product1, product2));

        String jsonContent = mapper.writeValueAsString(dummyProductPage);

        Mockito.when(productService.getAllProductsByCategory(category,0,5,"id")).thenReturn(dummyProductPage);

        mockMvc.perform(get("/products/{category}",category))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonContent));

        Mockito.verify(productService, Mockito.times(1)).getAllProductsByCategory(category,0,5,"id");


    }

    @Test
    public void testGetAllProductsByCategoryAndSubCategory() throws Exception {

        String category = "men";
        String subcategory = "shoes";

        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        Page<Product> dummyProductPage = new PageImpl<>(Arrays.asList(
                product1, product2));

        String jsonContent = mapper.writeValueAsString(dummyProductPage);

        Mockito.when(productService.getAllProductsByCategoryAndSubcategory(category,subcategory,0,5,"id")).thenReturn(dummyProductPage);

        mockMvc.perform(get("/products/{category}/{subcategory}",category,subcategory))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonContent));

        Mockito.verify(productService, Mockito.times(1)).getAllProductsByCategoryAndSubcategory(category,subcategory,0,5,"id");


    }


    @Test
    public void testGetAllProductsByCategoryAndSubCategoryAndSubDivision() throws Exception {

        String category = "men";
        String subcategory = "shoes";
        String subdivision = "sneakers";

        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        Page<Product> dummyProductPage = new PageImpl<>(Arrays.asList(
                product1, product2));

        String jsonContent = mapper.writeValueAsString(dummyProductPage);

        Mockito.when(productService.getAllProductsByCategoryAndSubcategoryAndSubdivsion(category,subcategory,subdivision,0,5,"id")).thenReturn(dummyProductPage);

        mockMvc.perform(get("/products/{category}/{subcategory}/{subdivision}",category,subcategory,subdivision))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonContent));

        Mockito.verify(productService, Mockito.times(1)).getAllProductsByCategoryAndSubcategoryAndSubdivsion(category,subcategory,subdivision,0,5,"id");


    }
}
