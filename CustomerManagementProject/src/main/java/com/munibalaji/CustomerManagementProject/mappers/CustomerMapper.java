package com.munibalaji.CustomerManagementProject.mappers;
import com.munibalaji.CustomerManagementProject.dtos.CustomerRequestDto;
import com.munibalaji.CustomerManagementProject.dtos.CustomerResponseDto;
import com.munibalaji.CustomerManagementProject.models.Customers;

public class CustomerMapper {

    public static Customers customerRequestDtoToEntity(CustomerRequestDto customerRequestDto){
        if (customerRequestDto == null){
            return null;
        }

        Customers customers = new Customers();
        customers.setCustomerName(customerRequestDto.getCustomerName());
        customers.setEmail(customerRequestDto.getEmail());
        customers.setPhone(customerRequestDto.getPhone());
        customers.setAddress(customerRequestDto.getAddress());

        return customers;
    }

    public static CustomerResponseDto entityToCustomerResponseDto(Customers customers){
        if(customers == null){
            return null;
        }

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customers.getId());
        customerResponseDto.setCustomerName(customers.getCustomerName());
        customerResponseDto.setEmail(customers.getEmail());
        customerResponseDto.setPhone(customers.getPhone());
        customerResponseDto.setAddress(customers.getAddress());

        return customerResponseDto;
    }
}
