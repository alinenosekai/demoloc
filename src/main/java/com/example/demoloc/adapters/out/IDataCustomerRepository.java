package com.example.demoloc.adapters.out;

import com.example.demoloc.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IDataCustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer c WHERE c.firstName = :firstName AND c.lastName = :lastName", nativeQuery = true)
    List<Customer> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    List<Customer> findByFullName(String fullName);
    List<Customer> findByEmail(String email);
}

