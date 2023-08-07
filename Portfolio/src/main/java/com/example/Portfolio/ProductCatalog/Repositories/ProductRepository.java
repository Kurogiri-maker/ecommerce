package com.example.Portfolio.ProductCatalog.Repositories;

import com.example.Portfolio.ProductCatalog.Models.Product;
import com.example.Portfolio.ProductCatalog.Models.ProductCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubDivision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAllByCategory(ProductCategory category,Pageable pageable);

    Page<Product> findAllByCategoryAndSubcategory(ProductCategory category, ProductSubCategory subCategory, Pageable pageable);

    Page<Product> findAllByCategoryAndSubcategoryAndSubdivision(ProductCategory category, ProductSubCategory subCategory, ProductSubDivision subDivision,Pageable pageable);

}
