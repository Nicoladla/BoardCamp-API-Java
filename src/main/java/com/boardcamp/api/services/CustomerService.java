package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.CustomerDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.exceptions.NotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> findCustomers() {
        List<CustomerModel> customers = customerRepository.findAll();
        return customers;
    }

    public CustomerModel findCustomerById(Long id) {
        CustomerModel customer = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer not found."));

        return customer;
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
