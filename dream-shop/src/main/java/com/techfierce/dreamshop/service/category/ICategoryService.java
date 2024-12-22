package com.techfierce.dreamshop.service.category;

import com.techfierce.dreamshop.model.Category;
import com.techfierce.dreamshop.requests.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long Id);
    Category getCategoryByName(String name);
    Category saveCategory(CategoryRequest categoryRequest);
    Category updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long categoryId);
    List<Category> getAllCategory();
}
