package com.techfierce.dreamshop.controller;

import com.techfierce.dreamshop.exceptions.ProductNotFoundException;
import com.techfierce.dreamshop.exceptions.ResourceNotFound;
import com.techfierce.dreamshop.model.Product;
import com.techfierce.dreamshop.requests.ProductAddRequest;
import com.techfierce.dreamshop.requests.ProductUpdateRequest;
import com.techfierce.dreamshop.response.ApiResponse;
import com.techfierce.dreamshop.service.product.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductAddRequest product){
        try {
            Product newProduct = iProductService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Success", newProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }



    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getProductById(){
        try {
            List<Product> products = iProductService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Success", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest product,
                                                     @PathVariable Long id){
        try {
            Product updatedProduct = iProductService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Success", updatedProduct));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable  Long id){
        try {
            iProductService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/product-by-id/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
        try {
            Product product = iProductService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/product-by-category/{categoryName}")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String categoryName){
        try {
            List<Product> product = iProductService.getProductsByCategory(categoryName);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/product-by-brand/{brandName}")
    public ResponseEntity<ApiResponse> getProductByBrand(@PathVariable String brandName){
        try {
            List<Product> product = iProductService.getProductsByBrand(brandName);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/product-by-category-brand/{categoryName}/{brandName}")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@PathVariable String categoryName,
                                                                    @PathVariable String brandName){
        try {
            List<Product> product = iProductService.getProductsByCategoryAndBrand(categoryName, brandName);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/product-by-name/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
        try {
            List<Product> product = iProductService.getProductsByName(name);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/product-by-brand-name/{brandName}/{name}")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@PathVariable String brandName,
                                                         @PathVariable String name){
        try {
            List<Product> product = iProductService.getProductsByBrandAndName(brandName, name);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/product-count-brand-name/{brandName}/{name}")
    public ResponseEntity<ApiResponse> getCountByBrandAndName(@PathVariable String brandName,
                                                              @PathVariable String name){
        try {
            var count = iProductService.countProductsByBrandAndName(brandName, name);
            return ResponseEntity.ok(new ApiResponse("Success", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }
}
























