package com.techfierce.dreamshop.service.product;

import com.techfierce.dreamshop.dto.ProductDto;
import com.techfierce.dreamshop.model.Product;
import com.techfierce.dreamshop.requests.ProductAddRequest;
import com.techfierce.dreamshop.requests.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(ProductAddRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest productUpdateRequest, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getProductDto(List<Product> products);

    ProductDto convertToDto(Product product);
}
