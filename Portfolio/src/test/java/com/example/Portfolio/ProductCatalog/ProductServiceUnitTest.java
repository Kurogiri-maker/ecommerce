package com.example.Portfolio.ProductCatalog;

import com.example.Portfolio.ProductCatalog.Models.Product;
import com.example.Portfolio.ProductCatalog.Models.ProductCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubDivision;
import com.example.Portfolio.ProductCatalog.Repositories.ProductRepository;
import com.example.Portfolio.ProductCatalog.Services.ProductService;
import com.example.Portfolio.ProductCatalog.Services.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
public class ProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp(){

        productService = new ProductServiceImpl(this.productRepository);
    }

    @Test
    public void testGetAllProductsByCategory_HasContent(){

        // Prepare mock data
        List<Product> products = new ArrayList<>();
        String category = "men";
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "id";
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

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
        Page<Product> page = new PageImpl<>(products);

        //Mock behavior of productRepository.findAllByCategory
        Mockito.when(productRepository.findAllByCategory(productCategory,paging)).thenReturn(page);

        //Test the service method
        Page<Product> pageResult = productService.getAllProductsByCategory(category,pageNo,pageSize,sortBy);

        // Verify that productRepository.findAllByCategory was called with the expected arguments
        verify(productRepository).findAllByCategory(productCategory,paging);

        // Verify the response and results
        Assertions.assertEquals(page,pageResult);

    }

    @Test
    public void testGetAllProductsByCategory_Empty(){

        // Prepare mock data
        String category = "men";
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "id";
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));


        // Create a mock Page<Product>
        Page<Product> page = Page.empty();

        //Mock behavior of productRepository.findAllByCategory
        Mockito.when(productRepository.findAllByCategory(productCategory,paging)).thenReturn(page);

        //Test the service method
        Page<Product> pageResult = productService.getAllProductsByCategory(category,pageNo,pageSize,sortBy);

        // Verify that productRepository.findAllByCategory was called with the expected arguments
        verify(productRepository).findAllByCategory(productCategory,paging);

        // Verify the response and results
        Assertions.assertEquals(page,pageResult);

    }

    @Test
    public void testGetAllProductsByCategoryAndSubcategory_HasContent(){

        // Prepare mock data
        List<Product> products = new ArrayList<>();
        String category = "men";
        String subcategory = "shoes";
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "id";
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        ProductSubCategory productSubCategory = ProductSubCategory.valueOf(subcategory.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

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
        Page<Product> page = new PageImpl<>(products);

        //Mock behavior of productRepository.findAllByCategoryAndSubcategory
        Mockito.when(productRepository.findAllByCategoryAndSubcategory(productCategory,productSubCategory,paging)).thenReturn(page);

        //Test the service method
        Page<Product> pageResult = productService.getAllProductsByCategoryAndSubcategory(category,subcategory,pageNo,pageSize,sortBy);

        // Verify that productRepository.findAllByCategoryAndSubcategory was called with the expected arguments
        verify(productRepository).findAllByCategoryAndSubcategory(productCategory,productSubCategory,paging);

        // Verify the response and results
        Assertions.assertEquals(page,pageResult);

    }

    @Test
    public void testGetAllProductsByCategoryAndSubcategory_Empty(){

        // Prepare mock data
        String category = "men";
        String subcategory = "shoes";
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "id";
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        ProductSubCategory productSubCategory = ProductSubCategory.valueOf(subcategory.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));


        // Create a mock Page<Product>
        Page<Product> page = Page.empty();

        //Mock behavior of productRepository.findAllByCategoryAndSubcategory
        Mockito.when(productRepository.findAllByCategoryAndSubcategory(productCategory,productSubCategory,paging)).thenReturn(page);

        //Test the service method
        Page<Product> pageResult = productService.getAllProductsByCategoryAndSubcategory(category,subcategory,pageNo,pageSize,sortBy);

        // Verify that productRepository.findAllByCategoryAndSubcategory was called with the expected arguments
        verify(productRepository).findAllByCategoryAndSubcategory(productCategory,productSubCategory,paging);

        // Verify the response and results
        Assertions.assertEquals(page,pageResult);

    }

    @Test
    public void testGetAllProductsByCategoryAndSubcategoryAndSubdivision_HasContent(){

        // Prepare mock data
        List<Product> products = new ArrayList<>();
        String category = "men";
        String subcategory = "shoes";
        String subdivision = "sneakers";
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "id";
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        ProductSubCategory productSubCategory = ProductSubCategory.valueOf(subcategory.toUpperCase());
        ProductSubDivision productSubDivision = ProductSubDivision.valueOf(subdivision.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

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
        Page<Product> page = new PageImpl<>(products);

        //Mock behavior of productRepository.findAllByCategoryAndSubcategoryAndSubdivision
        Mockito.when(productRepository.findAllByCategoryAndSubcategoryAndSubdivision(productCategory,productSubCategory,productSubDivision,paging)).thenReturn(page);

        //Test the service method
        Page<Product> pageResult = productService.getAllProductsByCategoryAndSubcategoryAndSubdivsion(category,subcategory,subdivision,pageNo,pageSize,sortBy);

        // Verify that productRepository.findAllByCategoryAndSubcategoryAndSubdivision was called with the expected arguments
        verify(productRepository).findAllByCategoryAndSubcategoryAndSubdivision(productCategory,productSubCategory,productSubDivision,paging);

        // Verify the response and results
        Assertions.assertEquals(page,pageResult);

    }

    @Test
    public void testGetAllProductsByCategoryAndSubcategoryAndSubdivision_Empty(){

        // Prepare mock data
        String category = "men";
        String subcategory = "shoes";
        String subdivision = "sneakers";
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "id";
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        ProductSubCategory productSubCategory = ProductSubCategory.valueOf(subcategory.toUpperCase());
        ProductSubDivision productSubDivision = ProductSubDivision.valueOf(subdivision.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));


        // Create a mock Page<Product>
        Page<Product> page = Page.empty();

        //Mock behavior of productRepository.findAllByCategoryAndSubcategoryAndSubdivision
        Mockito.when(productRepository.findAllByCategoryAndSubcategoryAndSubdivision(productCategory,productSubCategory,productSubDivision,paging)).thenReturn(page);

        //Test the service method
        Page<Product> pageResult = productService.getAllProductsByCategoryAndSubcategoryAndSubdivsion(category,subcategory,subdivision,pageNo,pageSize,sortBy);

        // Verify that productRepository.findAllByCategoryAndSubcategoryAndSubdivision was called with the expected arguments
        verify(productRepository).findAllByCategoryAndSubcategoryAndSubdivision(productCategory,productSubCategory,productSubDivision,paging);

        // Verify the response and results
        Assertions.assertEquals(page,pageResult);

    }


    @Test
    public void testGetProductById_Success(){
        // Create mock instances of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        //Mock behavior of productRepository.findById
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        //Test the service method
        Product productResult = productService.getProductById(1L);

        // Verify that productRepository.findById was called with the expected arguments
        verify(productRepository).findById(1L);

        // Verify the response and results
        Assertions.assertEquals(product,productResult);
    }

    @Test
    public void testGetProductById_NotFound(){

        // Create mock instances of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        //Mock behavior of productRepository.findById
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Test the service method and expect an exception
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1L);
        });

        // Verify that productRepository.findById was called with the expected arguments
        verify(productRepository).findById(1L);

        // Assert the exception message
        String expectedMessage = "Product not found"; // Adjust the message as needed
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testSaveProduct(){
        // Create mock instances of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        //Mock behavior of productRepository.save
        Mockito.when(productRepository.save(product)).thenReturn(product);

        //Test the service method
        Product productResult = productService.saveProduct(product);

        // Verify that productRepository.save was called with the expected arguments
        verify(productRepository).save(product);

        // Verify the response and results
        Assertions.assertEquals(product,productResult);

    }

    @Test
    public void testDeleteProduct(){
        // Create mock instances of Product with specific attributes
        Product product = new Product();
        product.setId(1L);

        //Mock behavior of productRepository.deleteById
        doNothing().when(productRepository).deleteById(1L);

        //Test the service method
        productService.deleteProduct(1L);

        // Verify that productRepository.deleteById was called with the expected arguments
        verify(productRepository).deleteById(1L);

        // Verify the response and results
        verifyNoMoreInteractions(productRepository);

    }
}
