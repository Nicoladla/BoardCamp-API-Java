package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.CustomerDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel createCustomer(CustomerDTO customerDTO) {
        boolean customerExistByCpf = customerRepository.existsByCpf(customerDTO.getCpf());

        if (customerExistByCpf)
            throw new ConflictException("The CPF has already been registered.");

        CustomerModel customer = new CustomerModel(customerDTO);
        customerRepository.save(customer);

        return customer;
    }
}
