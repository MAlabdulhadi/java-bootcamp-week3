package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Merchant;
import com.example.project3.Model.MerchantStock;
import com.example.project3.Model.Product;
import com.example.project3.service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStocks() {
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("done add"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors, @PathVariable int id) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdate) {
            return ResponseEntity.status(200).body("MerchantStocks update");
        }
        return ResponseEntity.status(400).body("id wrong");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id) {

        boolean isDelete = merchantStockService.deleteMerchantStock(id);
        if (isDelete) {
            return ResponseEntity.status(200).body("MerchantStocks delete");
        }
        return ResponseEntity.status(400).body("id Wrong");
    }

    @PutMapping("/add-product/{idMerchant}/{idProduct}/{stock}")
    public ResponseEntity addProduct(@PathVariable int idMerchant, @PathVariable int idProduct, @PathVariable int stock) {
        if (merchantStockService.addProuctToMerchant(idMerchant, idProduct, stock)) {
            return ResponseEntity.status(200).body("done add product");
        }
        return ResponseEntity.status(400).body("not add");
    }

}
