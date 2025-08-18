package com.example.demoloc.domain.ports;

import com.example.demoloc.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Integer id);
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    List<Customer> findByFullName(String fullName); 
    List<Customer> findByEmail(String email); 
    List<Customer> findAll();
    Customer save(Customer customer);
}
