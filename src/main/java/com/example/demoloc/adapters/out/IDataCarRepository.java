package com.example.demoloc.adapters.out;

import com.example.demoloc.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataCarRepository extends JpaRepository<Car, String> {
    
}

