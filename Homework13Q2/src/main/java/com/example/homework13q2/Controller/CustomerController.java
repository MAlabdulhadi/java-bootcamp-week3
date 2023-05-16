package com.example.homework13q2.Controller;


import com.example.homework13q2.ApiResponse.ApiResponse;
import com.example.homework13q2.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList getCustomer() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("add");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomers(@PathVariable int index, @RequestBody Customer customer) {
        customers.set(index, customer);
        return new ApiResponse("done update");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("done delete");
    }

    @PutMapping("/deposit/{userName}/{balance}")
    public ApiResponse depositMoney(@PathVariable String userName, @PathVariable double balance) {
        boolean checkUserName = false;
        for (Customer customer : customers) {
            if (customer.getUserName().equals(userName)) {
                checkUserName = true;
                customer.setBalance(customer.getBalance() + balance);
            }
        }
        if (checkUserName) {
            return new ApiResponse("done Deposit money for user" + userName);
        } else {
            return new ApiResponse("user wrong");
        }
    }

    @PutMapping("/withdraw/{userName}/{balance}")
    public ApiResponse withdrawMoney(@PathVariable String userName, @PathVariable double balance) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(userName)) {
                if (customer.getBalance() >= balance) {
                    customer.setBalance(customer.getBalance() - balance);
                    return new ApiResponse("done withdraw");
                } else {
                    return new ApiResponse("don't have balance enough");
                }
            }
        }
        return new ApiResponse("user wrong");
    }

}
