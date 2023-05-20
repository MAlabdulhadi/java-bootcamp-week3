package com.example.project3.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    //ID, name, price , categoryID.

    @NotNull(message = "id must not be empty")
    private int id;
    @NotEmpty(message = "name must not be empty")
    private String name;
    @NotNull(message = "price must not be empty")
    private double price;
    @NotNull(message = "id categoryID must not be empty")
    private int categoryID;


}
