package com.example.project3.Model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantStock {

    //ID , productid , merchantid , stock.
    @NotNull(message = "id must not be empty")
    private int id;
    @NotNull(message = "id product must not be empty")
    private int productId;
    @NotNull(message = "merchant id must not be empty")
    private int merchantId;
    @NotNull(message = "stock must not be empty")
    @PositiveOrZero(message = "must be 0 or more")
    private int stock;

}
