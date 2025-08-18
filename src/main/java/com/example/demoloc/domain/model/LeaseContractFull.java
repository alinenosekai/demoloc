package com.example.demoloc.domain.model;

import com.example.demoloc.domain.model.Customer;
import com.example.demoloc.domain.model.LeaseContract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaseContractFull {

    private LeaseContract contract;
    private Customer customer;
    private String idCar;
    
}
