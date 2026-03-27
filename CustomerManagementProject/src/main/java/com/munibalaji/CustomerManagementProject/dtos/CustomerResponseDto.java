package com.munibalaji.CustomerManagementProject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {

    private Long id;
    private String customerName;
    private String email;
    private Long phone;
    private String address;
}
