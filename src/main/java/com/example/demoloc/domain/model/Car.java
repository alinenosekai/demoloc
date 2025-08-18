package com.example.demoloc.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CAR")
public class Car {
    @Id
    private String id;
    private String model;
    private String immat;
    private boolean available;

}
