package com.example.project3.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

    //id , username , password , email , role , balance.

    @NotNull(message = "id must not be empty")
    private int id;
    @NotEmpty(message = "user name must not be empty")
    private String username;
    @NotEmpty()
//    @Pattern(regexp = "/^[A-Za-z0-9]*$/", message = "password must have characters and digits")
    private String password;
    @NotEmpty(message = "email must not be empty")
    @Email(message = "must be valid email ")
    private String email;
    @NotEmpty(message = "role must not be empty")
    @Pattern(regexp = "\\b(?:Admin|Customer)\\b")
    private String role;
    @Positive(message = "balance must to be positive")
    private double balance;

}
