package com.sample.util;

import com.sample.domain.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> createCustomer(Customer customer);
    
    Mono<Customer> getCustomerById(String id);
    
    Flux<Customer> getAllCustomers();
    
    Mono<Customer> updateCustomer(String id, Customer customer);
    
    Mono<Void> deleteCustomer(String id);
}

