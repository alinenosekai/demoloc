package com.example.demoloc.adapters.out;

import com.example.demoloc.domain.model.LeaseContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDataLeaseContractRepository extends JpaRepository<LeaseContract, Integer> {
    
    List<LeaseContract> findByIdCustomer(Integer idCustomer);
    List<LeaseContract> findByIdCar(String idCar);
}



