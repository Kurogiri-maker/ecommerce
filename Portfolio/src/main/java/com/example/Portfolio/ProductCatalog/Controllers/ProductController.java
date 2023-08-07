package com.example.Portfolio.ProductCatalog.Controllers;

import com.example.Portfolio.ProductCatalog.Models.Product;
import com.example.Portfolio.ProductCatalog.Services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@Slf4j
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService service){
        this.productService=service;
    }

     /** Get a paginated list of products by category.
            *
            * @param category The product category (e.g., "men", "women", "children").
            * @param page     The page number (default: 0).
            * @param size     The number of items per page (default: 5).
            * @param sortBy   The sorting criteria (default: "id").
            * @return A paginated list of products.
     */

    @GetMapping("/{category}")
    public ResponseEntity<Page<Product>> getAllProductsByCategory(@PathVariable String category,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy){

        Page<Product> pageResult = productService.getAllProductsByCategory(category, page,  size,  sortBy);
        return new ResponseEntity<>(pageResult,HttpStatus.OK);
    }

    /** Get a paginated list of products by category and subcategory.
     *
     * @param category The product category (e.g., "men", "women", "children").
     * @param subcategory The product category (e.g., "shoes", "clothes", "accessories").
     * @param page     The page number (default: 0).
     * @param size     The number of items per page (default: 5).
     * @param sortBy   The sorting criteria (default: "id").
     * @return A paginated list of products.
     */

    @GetMapping("/{category}/{subcategory}")
    public ResponseEntity<Page<Product>> getAllProductsBySubCategory(@PathVariable String category,
                                                        @PathVariable String subcategory,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy){
        Page<Product> pageResult = productService.getAllProductsByCategoryAndSubcategory(category, subcategory,page,  size,  sortBy);
        return new ResponseEntity<>(pageResult,HttpStatus.OK);
    }

    /** Get a paginated list of products by category ,subcategory and subdivision.
     *
     * @param category The product category (e.g., "men", "women", "children").
     * @param subcategory The product category (e.g., "shoes", "clothes", "accessories").
     * @param subdivision The product category (e.g., "sneakers", "shirts", "pants" , ...).
     * @param page     The page number (default: 0).
     * @param size     The number of items per page (default: 5).
     * @param sortBy   The sorting criteria (default: "id").
     * @return A paginated list of products.
     */

    @GetMapping("/{category}/{subcategory}/{subdivision}")
    public ResponseEntity<Page<Product>> getAllProductsBySubCategoryAndSubDivision(@PathVariable String category,
                                                                     @PathVariable String subcategory,
                                                                     @PathVariable String subdivision,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size,
                                                                     @RequestParam(defaultValue = "id") String sortBy){
        Page<Product> pageResult = productService.getAllProductsByCategoryAndSubcategoryAndSubdivsion(category, subcategory,subdivision,page,  size,  sortBy);
        return new ResponseEntity<>(pageResult,HttpStatus.OK);
    }

    /**
     * Get a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the given ID if found, or a "Not Found" response if not found.
     */

    @GetMapping("/{category}/{subcategory}/{subdivision}/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws RuntimeException{
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save a new product.
     *
     * @param product The product to be saved.
     * @return The created product if successful, or a "Bad Request" response if the save fails.
     */

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product createdProduct = productService.saveProduct(product);
        if(createdProduct != null){
            return new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     * Delete a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return A "No Content" response if the deletion is successful, or a "Not Found" response if the product doesn't exist.
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        if(productService.getProductById(id) != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
