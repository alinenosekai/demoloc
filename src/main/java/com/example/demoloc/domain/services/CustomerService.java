package com.example.demoloc.domain.service;

import com.example.demoloc.domain.model.Customer;
import com.example.demoloc.domain.ports.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Customer> findByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    public List<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Customer> listAllCustomers() {
        return repository.findAll();
    }

    @Transactional
    public Customer addCustomer(String lastName, String firstName, String address, String email,
                                String phone, LocalDate dateBirth, String licenseCar) {
        Customer customer = new Customer();
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setDateBirth(dateBirth);
        customer.setLicenceCar(licenseCar);

        return repository.save(customer);
    }
}
