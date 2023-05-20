package com.example.project3.Controller;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.MerchantStock;
import com.example.project3.Model.Product;
import com.example.project3.Model.User;
import com.example.project3.service.MerchantService;
import com.example.project3.service.MerchantStockService;
import com.example.project3.service.ProductService;
import com.example.project3.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getUser() {
        ArrayList<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("done add"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, Errors errors, @PathVariable int id) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = userService.updateUser(id, user);
        if (isUpdate) {
            return ResponseEntity.status(200).body("user update");
        }
        return ResponseEntity.status(400).body("id wrong");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {

        boolean isDelete = userService.deleteUser(id);
        if (isDelete) {
            return ResponseEntity.status(200).body("user delete");
        }
        return ResponseEntity.status(400).body("id Wrong");
    }

    @PutMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {
        if (userService.searchId(userId)) {
            if (merchantStockService.hasStock(merchantId, productId)) {
                if (productService.getPrice(productId) != -1) {
                    if (userService.getBalance(userId) >= productService.getPrice(productId)) {
                        userService.reduceBalance(userId, productService.getPrice(productId));
                        merchantStockService.reducStock(productId, merchantId);
                        return ResponseEntity.status(200).body("ok buy");
                    }
                }
            }
        }
        return ResponseEntity.status(400).body("not buy");
    }

}
