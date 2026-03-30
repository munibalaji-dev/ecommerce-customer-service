package com.munibalaji.CustomerManagementProject.services;

import com.munibalaji.CustomerManagementProject.dtos.CustomerRequestDto;
import com.munibalaji.CustomerManagementProject.dtos.CustomerResponseDto;
import com.munibalaji.CustomerManagementProject.exceptions.ResourceNotFoundException;
import com.munibalaji.CustomerManagementProject.mappers.CustomerMapper;
import com.munibalaji.CustomerManagementProject.models.Customers;
import com.munibalaji.CustomerManagementProject.repositories.CustomerRepository;
import com.munibalaji.CustomerManagementProject.repositories.specifications.CustomerSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        // Here am checking the validation checks, it allows the data to save only if checks will pass
        validationsCheckingForUniqueness(customerRequestDto);

        Customers saved = CustomerMapper.customerRequestDtoToEntity(customerRequestDto);
        Customers customers = customerRepository.save(saved);

        return CustomerMapper.entityToCustomerResponseDto(customers);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {

        Customers getById = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("customer not found with your id check it once and try again"));

        return CustomerMapper.entityToCustomerResponseDto(getById);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        List<Customers> getAll = customerRepository.findAll();

        return getAll.stream()
                .map(CustomerMapper::entityToCustomerResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto) {

        Customers customers = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with your id try again"));

        customers.setCustomerName(customerRequestDto.getCustomerName());
        customers.setEmail(customerRequestDto.getEmail());
        customers.setPhone(customerRequestDto.getPhone());
        customers.setAddress(customerRequestDto.getAddress());

        Customers updatedCustomer = customerRepository.save(customers);

        return CustomerMapper.entityToCustomerResponseDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDto deleteCustomerById(Long id) {

        Customers customers = customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("customer not found to delete with your id try again"));
        customerRepository.delete(customers);

        return null;
    }


    private void validationsCheckingForUniqueness(CustomerRequestDto customerRequestDto){
        if (customerRepository.existsByEmail(customerRequestDto.getEmail())){
            throw new ResourceNotFoundException("Email "+ customerRequestDto.getEmail()+ " is already exists");
        }

        if(customerRepository.existsByPhone(customerRequestDto.getPhone())){
            throw new ResourceNotFoundException("Phone number "+ customerRequestDto.getPhone()+ " is already in use");
        }
    }

    public Page<CustomerResponseDto> searchCustomers(String name, String email, String address, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Customers> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        if (name != null){
            specification = specification.and(CustomerSpecification.hasName(name));
        }

        if (email != null){
            specification = specification.and(CustomerSpecification.hasEmail(email));
        }

        if (address != null){
            specification = specification.and(CustomerSpecification.hasAdress(address));
        }

        return customerRepository.findAll(specification, pageable)
                .map(CustomerMapper::entityToCustomerResponseDto);
    }
}
