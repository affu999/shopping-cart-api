package com.techfierce.dreamshop.service.product;

import com.techfierce.dreamshop.exceptions.ProductNotFoundException;
import com.techfierce.dreamshop.exceptions.ResourceNotFound;
import com.techfierce.dreamshop.model.Category;
import com.techfierce.dreamshop.model.Product;
import com.techfierce.dreamshop.repository.CategoryRepository;
import com.techfierce.dreamshop.repository.ProductRepository;
import com.techfierce.dreamshop.requests.ProductAddRequest;
import com.techfierce.dreamshop.requests.ProductUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(ProductAddRequest product) {
        Category category = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName())).orElseGet(
                () -> {
                    Category newCategory = new Category(product.getCategory().getName());
                    return categoryRepository.save(newCategory);
                }
        );
        product.setCategory(category);
        return productRepository.save(createProduct(product));
    }

    public Product createProduct(ProductAddRequest request ){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                request.getCategory()
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ProductNotFoundException("Product Not Found!");
        });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest productUpdateRequest, Long productId) {
        return productRepository.findById(productId).map(
                (existingProduct) -> updateExistingProduct(existingProduct, productUpdateRequest)
        ).map(productRepository::save).orElseThrow(() ->
            new ResourceNotFound("Product Not Found"));
    }

    public Product updateExistingProduct(Product existingProduct, ProductUpdateRequest productUpdateRequest){
        existingProduct.setBrand(productUpdateRequest.getBrand());
        existingProduct.setName(productUpdateRequest.getName());
        existingProduct.setPrice(productUpdateRequest.getPrice());
        existingProduct.setDescription(productUpdateRequest.getDescription());
        existingProduct.setInventory(productUpdateRequest.getInventory());
        existingProduct.setCategory(productUpdateRequest.getCategory());
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findBycategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}
