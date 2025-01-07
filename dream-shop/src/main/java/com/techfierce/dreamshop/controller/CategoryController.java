package com.techfierce.dreamshop.controller;

import com.techfierce.dreamshop.exceptions.AlreadyExistsException;
import com.techfierce.dreamshop.exceptions.ResourceNotFound;
import com.techfierce.dreamshop.model.Category;
import com.techfierce.dreamshop.requests.CategoryRequest;
import com.techfierce.dreamshop.response.ApiResponse;
import com.techfierce.dreamshop.service.category.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${api.prefix}/categories")
public class CategoryController {
    private final ICategoryService iCategoryService;

    public CategoryController(ICategoryService icategoryService) {
        this.iCategoryService = icategoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try {
            List<Category> allCategories = iCategoryService.getAllCategory();
            return ResponseEntity.ok(new ApiResponse("Success", allCategories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error" + e.getMessage(), null)
            );
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveCategory(@RequestBody CategoryRequest category){
        try {
            Category newCategory = iCategoryService.saveCategory(category);
            return ResponseEntity.ok(new ApiResponse("category added successfully!", newCategory));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/category-by-id/{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId){
        try {
            Category category = iCategoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Category found!", category));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("Not Found!", null)
            );
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/category-by-name/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@RequestParam String name){
        try {
            Category category = iCategoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Success", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @PutMapping("/update-category")
    public ResponseEntity<ApiResponse> updateCateory(@RequestBody CategoryRequest category){
        try {
            Category updatedCategory = iCategoryService.updateCategory(category);
            return ResponseEntity.ok(new ApiResponse("Success", updatedCategory));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId){
        try {
            iCategoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(e.getMessage(), null)
            );
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Error: " + e.getMessage(), null)
            );
        }
    }

}

























