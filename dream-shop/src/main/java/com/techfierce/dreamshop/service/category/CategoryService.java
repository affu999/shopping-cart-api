package com.techfierce.dreamshop.service.category;

import com.techfierce.dreamshop.exceptions.AlreadyExistsException;
import com.techfierce.dreamshop.exceptions.ResourceNotFound;
import com.techfierce.dreamshop.model.Category;
import com.techfierce.dreamshop.repository.CategoryRepository;
import com.techfierce.dreamshop.requests.CategoryRequest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category Not Found!")
        );
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    @Override
    public Category saveCategory(CategoryRequest categoryRequest) {
        return Optional.of(categoryRequest).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map((c) -> categoryRepository.save(getNewCategory(c)))
                .orElseThrow(() -> new AlreadyExistsException("Category Already Exists!"));
    }

    public Category getNewCategory(CategoryRequest categoryRequest){
        return new Category(categoryRequest.getName());
    }

    @Override
    public Category updateCategory(CategoryRequest categoryRequest) {
        return Optional.ofNullable(getCategoryById(categoryRequest.getId())).map((category) -> {
            category.setName(categoryRequest.getName());
            return category;
        }).orElseThrow(() -> new ResourceNotFound("Category Not Found!"));
    }

    public Category updateExistingCategory(Category existingcategory, CategoryRequest categoryRequest){
        existingcategory.setName(categoryRequest.getName());
        return existingcategory;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).ifPresentOrElse(
                categoryRepository::delete, () -> {
                    throw new ResourceNotFound("CategoryNot Found");
                }
        );
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
