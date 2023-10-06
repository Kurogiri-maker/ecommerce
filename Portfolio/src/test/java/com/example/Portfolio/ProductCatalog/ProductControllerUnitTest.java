package com.example.Portfolio.ProductCatalog;

import com.example.Portfolio.ProductCatalog.Controllers.ProductController;
import com.example.Portfolio.ProductCatalog.Models.Product;
import com.example.Portfolio.ProductCatalog.Models.ProductCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubDivision;
import com.example.Portfolio.ProductCatalog.Services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
public class ProductControllerUnitTest {

    @Mock
    private   ProductService productService;



    private ProductController productController;


    @BeforeEach
    void setUp() {
        productController = new ProductController(this.productService);
    }

    @Test
    public void testGetAllProductsByCategory(){

        // Prepare mock data
        List<Product> products = new ArrayList<>();

        // Create mock instances of Product with specific attributes
        Product product1 = new Product();
        product1.setId(1L);
        product1.setCategory(ProductCategory.MEN);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setCategory(ProductCategory.MEN);

        products.add(product1);
        products.add(product2);

        // Create a mock Page<Product>
        Page<Product> pageResult = new PageImpl<>(products);

        // Mock behavior of productService.getAllProductsByCategory
        when(productService.getAllProductsByCategory("men", 0, 5, "id"))
                .thenReturn(pageResult);

        // Test the controller method
        ResponseEntity<Page<Product>> response = productController.getAllProductsByCategory("men", 0, 5, "id");

        // Verify that productService.getAllProductsByCategory was called with the expected arguments
        verify(productService).getAllProductsByCategory("men", 0, 5, "id");

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(pageResult, response.getBody());
    }

    @Test
    public void testGetAllProductsByCategoryAndSubCategory(){
        // Prepare mock data
        List<Product> products = new ArrayList<>();

        // Create mock instances of Product with specific attributes
        Product product1 = new Product();
        product1.setId(1L);
        product1.setCategory(ProductCategory.MEN);
        product1.setSubcategory(ProductSubCategory.SHOES);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setCategory(ProductCategory.MEN);
        product2.setSubcategory(ProductSubCategory.SHOES);


        products.add(product1);
        products.add(product2);

        // Create a mock Page<Product>
        Page<Product> pageResult = new PageImpl<>(products);

        // Mock behavior of productService.getAllProductsByCategory
        when(productService.getAllProductsByCategoryAndSubcategory("men", "shoes", 0, 5, "id"))
                .thenReturn(pageResult);

        // Test the controller method
        ResponseEntity<Page<Product>> response = productController.getAllProductsBySubCategory("men", "shoes",0, 5, "id");

        // Verify that productService.getAllProductsByCategory was called with the expected arguments
        verify(productService).getAllProductsByCategoryAndSubcategory("men","shoes", 0, 5, "id");

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(pageResult, response.getBody());

    }


    @Test
    public void testGetAllProductsBySubCategoryAndSubDivision(){
        // Prepare mock data
        List<Product> products = new ArrayList<>();

        // Create mock instances of Product with specific attributes
        Product product1 = new Product();
        product1.setId(1L);
        product1.setCategory(ProductCategory.MEN);
        product1.setSubcategory(ProductSubCategory.SHOES);
        product1.setSubdivision(ProductSubDivision.SNEAKERS);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setCategory(ProductCategory.MEN);
        product2.setSubcategory(ProductSubCategory.SHOES);
        product2.setSubdivision(ProductSubDivision.SNEAKERS);



        products.add(product1);
        products.add(product2);

        // Create a mock Page<Product>
        Page<Product> pageResult = new PageImpl<>(products);

        // Mock behavior of productService.getAllProductsByCategoryAndSubcategoryAndSubdivsion
        when(productService.getAllProductsByCategoryAndSubcategoryAndSubdivsion("men", "shoes","sneakers", 0, 5, "id"))
                .thenReturn(pageResult);

        // Test the controller method
        ResponseEntity<Page<Product>> response = productController.getAllProductsBySubCategoryAndSubDivision("men", "shoes","sneakers",0, 5, "id");

        // Verify that productService.getAllProductsByCategory was called with the expected arguments
        verify(productService).getAllProductsByCategoryAndSubcategoryAndSubdivsion("men","shoes","sneakers", 0, 5, "id");

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(pageResult, response.getBody());

    }


    @Test
    public void testGetProductById_Success(){
        // Create mock instance of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        // Mock behavior of productService.getProductById
        when(productService.getProductById(1L)).thenReturn(product);

        // Test the controller method
        ResponseEntity<Product> response = productController.getProductById(1L);

        // Verify that productService.getProductById was called with the expected arguments
        verify(productService).getProductById(1L);

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(product, response.getBody());

    }

    @Test
    public void testGetProductById_NotFound(){

        // Mock behavior of productService.getProductById
        when(productService.getProductById(1L)).thenThrow(new RuntimeException("Product not found"));

        // Test the controller method
        ResponseEntity<Product> response = productController.getProductById(1L);

        // Verify that productService.getProductById was called with the expected arguments
        verify(productService).getProductById(1L);

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

    }


    @Test
    public void testSaveProduct_Success(){
        // Create mock instance of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        // Mock behavior of productService.getProductById
        when(productService.saveProduct(product)).thenReturn(product);

        // Test the controller method
        ResponseEntity<Product> response = productController.saveProduct(product);

        // Verify that productService.getProductById was called with the expected arguments
        verify(productService).saveProduct(product);

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(product,response.getBody());

    }

    @Test
    public void testSaveProduct_BadRequest(){
        // Create mock instance of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        // Mock behavior of productService.getProductById
        when(productService.saveProduct(product)).thenReturn(null);

        // Test the controller method
        ResponseEntity<Product> response = productController.saveProduct(product);

        // Verify that productService.getProductById was called with the expected arguments
        verify(productService).saveProduct(product);

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());

    }


    @Test
    public void testDeleteProduct_Success(){
        // Create mock instance of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        // Mock behavior of productService.getProductById
        when(productService.getProductById(1L)).thenReturn(product);
        doNothing().when(productService).deleteProduct(1L);

        // Test the controller method
        ResponseEntity<?> response = productController.deleteProduct(1L);

        // Verify that productService.getProductById was called with the expected arguments
        verify(productService).getProductById(1L);
        verify(productService).deleteProduct(1L);

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testDeleteProduct_NotFound(){
        // Create mock instance of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        // Mock behavior of productService.getProductById
        when(productService.getProductById(1L)).thenReturn(null);

        // Test the controller method
        ResponseEntity<?> response = productController.deleteProduct(1L);

        // Verify that productService.getProductById was called with the expected arguments
        verify(productService).getProductById(1L);

        // Verify the response and results
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }
}
