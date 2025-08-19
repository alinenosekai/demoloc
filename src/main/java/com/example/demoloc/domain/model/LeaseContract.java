package com.example.demoloc.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "LEASE_CONTRACT")
public class LeaseContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String refLeaseContract;
    private LocalDateTime startContract;
    private LocalDateTime endContract;
    private String idCar;
    private Integer idCustomer;
    private String statusLeaseContract; 
}

