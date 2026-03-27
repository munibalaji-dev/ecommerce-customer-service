package com.munibalaji.CustomerManagementProject.services;

import com.munibalaji.CustomerManagementProject.dtos.CustomerRequestDto;
import com.munibalaji.CustomerManagementProject.dtos.CustomerResponseDto;

import java.util.List;

public interface CustomerService {


    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto getCustomerById(Long id);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto);

    CustomerResponseDto deleteCustomerById(Long id);

}
