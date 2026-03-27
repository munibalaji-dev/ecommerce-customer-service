package com.munibalaji.CustomerManagementProject.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customers extends BaseModel{

    private String customerName;
    private String email;
    private Long phone;
    private String address;
}
