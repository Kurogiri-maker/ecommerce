package com.example.Portfolio.ProductCatalog.Services;

import com.example.Portfolio.ProductCatalog.Models.Product;
import com.example.Portfolio.ProductCatalog.Models.ProductCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubCategory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    //List<Product> getAllProducts();

    Page<Product> getAllProductsByCategory(String category,int pageNo, int pageSize, String sortBy);
    Page<Product> getAllProductsByCategoryAndSubcategory(String category, String subCategory,int pageNo, int pageSize, String sortBy);

    Page<Product> getAllProductsByCategoryAndSubcategoryAndSubdivsion(String category, String subcategory, String subdivision,int pageNo, int pageSize, String sortBy);


    Product getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);
}
