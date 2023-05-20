package com.example.project3.Controller;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Category;
import com.example.project3.Model.User;
import com.example.project3.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory() {
        ArrayList<User> users = categoryService.getCategories();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("done add"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody Category category, Errors errors, @PathVariable int id) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = categoryService.updateCategory(id, category);
        if (isUpdate) {
            return ResponseEntity.status(200).body("Category update");
        }
        return ResponseEntity.status(400).body("id wrong");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {

        boolean isDelete = categoryService.deleteCategory(id);
        if (isDelete) {
            return ResponseEntity.status(200).body("category delete");
        }
        return ResponseEntity.status(400).body("id Wrong");
    }

}
