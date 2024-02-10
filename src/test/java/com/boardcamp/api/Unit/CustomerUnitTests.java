package com.boardcamp.api.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.services.CustomerService;

@SpringBootTest
public class CustomerUnitTests {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void givenAValidCustomerId_whenWantFindCustomer_thenReturnCustomer() {
        Long id = 1L;
        CustomerModel customer = new CustomerModel(id, "name", "12345678900");

        doReturn(Optional.of(customer)).when(customerRepository).findById(any());

        CustomerModel result = customerService.findCustomerById(id);

        verify(customerRepository, times(1)).findById(any());
        assertEquals(customer, result);
    }
}
