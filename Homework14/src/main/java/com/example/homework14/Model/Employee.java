package com.example.homework14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Data
public class Employee {
    @NotEmpty(message = "id cann\'t be empty")
    @Size(min = 3, message = "id Length must be more than 2")
    private String id;
    @NotEmpty(message = "name cann\'t be empty")
    @Size(min = 5, message = "name Length must be more than 4")
    private String name;
    @NotNull(message = "age cann\'t be empty")
    @Min(value = 26, message = "age must be more than 25")
    @PositiveOrZero(message = "must be write number positive and not zero")
    private int age;
    @NotNull(message = "role cann\'t be empty")
    @Pattern(regexp = "\\b(?:supervisor|coordinator)\\b", message = "must be supervisor or coordinator ")//
    private String position;
    @AssertFalse(message = "on leave must be false")
    private boolean onLeave;
    @NotNull(message = "employment Year cann\'t be empty")
    @Max(value = 2023, message = "the date is future !!\"")
    @DateTimeFormat(pattern = "yyyy")
    @Positive(message = "must be write number")
    private int employmentYear;
    @NotNull(message = "annual Leave cann\'t be empty")
    @PositiveOrZero(message = "must be write number")
    private int annualLeave;


}
