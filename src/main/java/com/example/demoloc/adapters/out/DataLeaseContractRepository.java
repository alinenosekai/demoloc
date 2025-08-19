package com.example.demoloc.adapters.out;

import com.example.demoloc.domain.model.LeaseContract;
import com.example.demoloc.domain.ports.LeaseContractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataLeaseContractRepository implements LeaseContractRepository {

    private final IDataLeaseContractRepository iDataLeaseContract;

    public DataLeaseContractRepository(IDataLeaseContractRepository iDataLeaseContract) {
        this.iDataLeaseContract = iDataLeaseContract;
    }

    @Override
    public Optional<LeaseContract> findById(Integer id) {
        return iDataLeaseContract.findById(id);
    }

    @Override
    public List<LeaseContract> findAll() {
        return iDataLeaseContract.findAll();
    }

    public List<LeaseContract> findByIdCustomer(Integer idCustomer) {
        return iDataLeaseContract.findByIdCustomer(idCustomer);
    }

    public List<LeaseContract> findByIdCar(Integer idCar) {
        return iDataLeaseContract.findByIdCar(idCar);
    }

    @Override
    public LeaseContract save(LeaseContract leaseContract) {
        return iDataLeaseContract.save(leaseContract);
    }
}

