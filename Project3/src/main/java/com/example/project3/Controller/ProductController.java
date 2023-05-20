package com.example.project3.Controller;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Category;
import com.example.project3.Model.Product;
import com.example.project3.Model.User;
import com.example.project3.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProducts() {
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("done add"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product, Errors errors, @PathVariable int id) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = productService.updateProduct(id, product);
        if (isUpdate) {
            return ResponseEntity.status(200).body("Product update");
        }
        return ResponseEntity.status(400).body("id wrong");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {

        boolean isDelete = productService.deleteProduct(id);
        if (isDelete) {
            return ResponseEntity.status(200).body("Product delete");
        }
        return ResponseEntity.status(400).body("id Wrong");
    }


}
