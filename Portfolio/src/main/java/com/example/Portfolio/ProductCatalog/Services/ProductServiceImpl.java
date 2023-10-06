package com.example.Portfolio.ProductCatalog.Services;

import com.example.Portfolio.ProductCatalog.Models.Product;
import com.example.Portfolio.ProductCatalog.Models.ProductCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubCategory;
import com.example.Portfolio.ProductCatalog.Models.ProductSubDivision;
import com.example.Portfolio.ProductCatalog.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public  ProductServiceImpl(ProductRepository repository){
        this.productRepository=repository;
    }


    @Override
    public Page<Product> getAllProductsByCategory(String category,int pageNo, int pageSize, String sortBy){
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult=  productRepository.findAllByCategory(productCategory,paging);
        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return Page.empty();
        }
    }

    @Override
    public Page<Product> getAllProductsByCategoryAndSubcategory(String category, String subcategory,int pageNo, int pageSize, String sortBy) {
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        ProductSubCategory productSubCategory = ProductSubCategory.valueOf(subcategory.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult=  productRepository.findAllByCategoryAndSubcategory(productCategory,productSubCategory,paging);
        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return Page.empty();
        }
    }

    @Override
    public Page<Product> getAllProductsByCategoryAndSubcategoryAndSubdivsion(String category, String subcategory, String subdivision, int pageNo, int pageSize, String sortBy) {
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
        ProductSubCategory productSubCategory = ProductSubCategory.valueOf(subcategory.toUpperCase());
        ProductSubDivision productSubDivision = ProductSubDivision.valueOf(subdivision.toUpperCase());
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult=  productRepository.findAllByCategoryAndSubcategoryAndSubdivision(productCategory,productSubCategory,productSubDivision,paging);
        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return Page.empty();
        }
    }

    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
