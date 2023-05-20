package com.example.project3.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Merchant {
    //ID , name.
    @NotNull(message = "id must not be empty")
    private int id;
    @NotEmpty(message = "name must not be empty")
    private String name;

}
