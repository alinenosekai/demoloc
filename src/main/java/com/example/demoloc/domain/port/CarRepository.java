package com.example.demoloc.domain.ports;

import com.example.demoloc.domain.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarRepository {
    Optional<Car> findById(String id);
    List<Car> findAll();
    Car save(Car car);
}
