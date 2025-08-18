package com.example.demoloc.domain.service;

import com.example.demoloc.adapters.in.exception.ResourceNotFoundException;
import com.example.demoloc.domain.model.Car;
import com.example.demoloc.domain.ports.CarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {
    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> listAvailableCars() {
        return repository.findAll().stream()
                .filter(Car::isAvailable)
                .toList();
    }

    @Transactional
    public Car rentCar(String id) {
        Car car = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for ID: " + id));
        if (!car.isAvailable()) {
            throw new RuntimeException("Car with ID " + id + " is not available for rent.");

        }
        car.setAvailable(false);
        return repository.save(car);
    }

    @Transactional
    public Car returnCar(String id) {
        Car car = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for ID: " + id));
       
        car.setAvailable(true);
        return repository.save(car);
    }
}
