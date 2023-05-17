package com.example.homework14.Controller;

import com.example.homework14.ApiResponse.ApiResponse;
import com.example.homework14.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Employee> getEmployee() {
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("done add"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @Valid @RequestBody Employee employee, Errors errors) {

        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massage));
        }
        employees.set(index, employee);
        return ResponseEntity.status(200).body(new ApiResponse("done update"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index) {
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("done delete"));
    }

    @PutMapping("/requestleave/{id}")
    public ResponseEntity requestLeaveById(@PathVariable String id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                if (employee.isOnLeave()) {
                    return ResponseEntity.status(400).body(new ApiResponse("already on leave"));
                } else if (employee.getAnnualLeave() > 0) {
                    employee.setAnnualLeave(employee.getAnnualLeave() - 1);
                    employee.setOnLeave(true);
                    return ResponseEntity.status(200).body(new ApiResponse("done"));
                } else {
                    return ResponseEntity.status(400).body(new ApiResponse("not have Annual leave"));
                }
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }
    
}


