package com.munibalaji.CustomerManagementProject.services;

import com.munibalaji.CustomerManagementProject.dtos.CustomerRequestDto;
import com.munibalaji.CustomerManagementProject.dtos.CustomerResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {


    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto getCustomerById(Long id);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto);

    CustomerResponseDto deleteCustomerById(Long id);

    Page<CustomerResponseDto> searchCustomers(String name, String email, String address, int page, int size, String sortBy, String direction);

}
