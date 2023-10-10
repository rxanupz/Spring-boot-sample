package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.CustomerRepository;
import com.sample.domain.Customer;
import com.sample.util.CustomerService;

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
    public Customer createCustomer(Customer customer) {
        // Implement logic to create a new customer
        // You can perform validation and any necessary business logic here
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        // Implement logic to retrieve a customer by ID
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        // Implement logic to retrieve all customers
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(String id, Customer customer) {
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
