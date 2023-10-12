package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.CustomerRepository;
import com.sample.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<Customer> createCustomer(Customer customer) {
        // Implement logic to create a new customer
        // You can perform validation and any necessary business logic here
        return customerRepository.save(customer);
    }

    public Mono<Customer> getCustomerById(String id) {
        // Implement logic to retrieve a customer by ID
        Mono<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        // Implement logic to retrieve all customers
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> updateCustomer(String id, Customer customer) {
        // Implement logic to update a customer by ID
        if (customerRepository.existsById(id)) {
            customer.setId(id); // Ensure the updated ID matches the existing record
            return customerRepository.save(customer);
        } else {
            return null; // Customer not found
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        // Implement logic to delete a customer by ID
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false; // Customer not found
        }
    }
}
