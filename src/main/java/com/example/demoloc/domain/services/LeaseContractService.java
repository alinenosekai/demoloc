package com.example.demoloc.domain.service;

import com.example.demoloc.adapters.in.exception.ResourceNotFoundException;
import com.example.demoloc.domain.model.Car;
import com.example.demoloc.domain.model.Customer;
import com.example.demoloc.domain.model.LeaseContract;
import com.example.demoloc.domain.model.LeaseContractFull;
import com.example.demoloc.domain.ports.CarRepository;
import com.example.demoloc.domain.ports.CustomerRepository;
import com.example.demoloc.domain.ports.LeaseContractRepository;
com.example.demoloc.domain.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaseContractService {
    private final LeaseContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final CustomerService customerService;


    @Autowired
    public LeaseContractService(LeaseContractRepository contractRepository,
                                CustomerRepository customerRepository,
                                CarRepository carRepository,
                                CustomerService customerService) {
        this.contractRepository = contractRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.customerService = customerService;
    }

    public List<LeaseContract> listAllLeaseContract() {
        return contractRepository.findAll();
    }

    public List<LeaseContract> findByIdCar(String idCar) {
        return contractRepository.findByIdCar(idCar);
    }

    public List<LeaseContract> findByIdCustomer(Integer idCustomer) {
         return contractRepository.findByIdCustomer(idCustomer);    
    }

    @Transactional
    public LeaseContract addLeaseContract(LeaseContractFull leaseContractFull) {

        if (leaseContractFull == null) {
            throw new IllegalArgumentException("Le contrat ne peut pas être null");
        }

        Car car = carRepository.findById(leaseContractFull.getIdCar())
                                .orElseThrow(() ->  new ResourceNotFoundException("Car not found for ID: " + leaseContractFull.getIdCar()));

        Customer customer = leaseContractFull.getCustomer();
        if (customer == null) {
            throw new ResourceNotFoundException("Customer information is missing");
        }

        Customer customerSaved = customerService.addCustomer(
            customer.getLastName(), 
            customer.getFirstName(),
            customer.getAddress(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getDateBirth(),
            customer.getLicenseCar()
        );

        LeaseContract newLeaseContract = leaseContractFull.getContract();
        newLeaseContract.setIdCar(leaseContractFull.getIdCar());
        newLeaseContract.setIdCustomer(customerSaved.getId());
        newLeaseContract.setStatusLeaseContract("CREATED");

        return contractRepository.save(newLeaseContract);
    }

    @Transactional
    public void changeStatus(Integer id, String newStatus) {
        LeaseContract leaseContract = contractRepository.findById(id)
                                                        .orElseThrow(() -> new ResourceNotFoundException("Lease contract not found for ID: " + id));

        leaseContract.setStatusLeaseContract(newStatus);
        contractRepository.save(leaseContract);
    }
}
