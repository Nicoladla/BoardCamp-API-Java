package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.DTOs.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.services.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getCustomers() {
        List<CustomerModel> customers = customerService.findCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping({ "/{id}" })
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("id") Long id) {
        CustomerModel customer = customerService.findCustomerById(id);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerModel> postCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerModel customer = customerService.createCustomer(customerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
