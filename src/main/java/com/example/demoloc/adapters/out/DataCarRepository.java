package com.example.demoloc.adapters.out;

import com.example.demoloc.domain.model.Car;
import com.example.demoloc.domain.ports.CarRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataCarRepository implements CarRepository {

    private final IDataCarRepository iDataCar;

    public DataCarRepository(IDataCarRepository iDataCar) {
        this.iDataCar = iDataCar;
    }

    @Override
    public Optional<Car> findById(String id) {
        return iDataCar.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return iDataCar.findAll();
    }

    @Override
    public Car save(Car car) {
        return iDataCar.save(car);
    }
}
