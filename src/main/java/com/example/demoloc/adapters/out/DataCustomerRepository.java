package com.example.demoloc.adapters.out;

import com.example.demoloc.domain.model.Customer;
import com.example.demoloc.domain.ports.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataCustomerRepository implements CustomerRepository {

    private final IDataCustomerRepository iDataCustomer;

    public DataCustomerRepository(IDataCustomerRepository iDataCustomer) {
        this.iDataCustomer = iDataCustomer;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return iDataCustomer.findById(id);
    }

    @Override
    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
         return iDataCustomer.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Customer> findByFullName(String fullName) {
        String[] nameParts = fullName.split(" ", 2); // limite à 2 parties
        if (nameParts.length < 2) {
            throw new IllegalArgumentException("Full name must contain first name and last name");
        }
        return iDataCustomer.findByFirstNameAndLastName(nameParts[0], nameParts[1]);
    }

    @Override
    public List<Customer> findByEmail(String email) {
        return iDataCustomer.findByEmail(email);
    }

    @Override
    public List<Customer> findAll() {
        return iDataCustomer.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return iDataCustomer.save(customer);
    }
}
