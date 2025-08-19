package com.example.demoloc.domain.ports;

import com.example.demoloc.domain.model.LeaseContract;
import java.util.List;
import java.util.Optional;

public interface LeaseContractRepository {
    Optional<LeaseContract> findById(Integer id);
    List<LeaseContract> findByIdCustomer(Integer idCustomer);
    List<LeaseContract> findByIdCar(String idCar);
    List<LeaseContract> findAll();
    LeaseContract save(LeaseContract leaseContract);
}


