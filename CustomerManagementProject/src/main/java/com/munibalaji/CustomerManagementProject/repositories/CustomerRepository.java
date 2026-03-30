package com.munibalaji.CustomerManagementProject.repositories;

import com.munibalaji.CustomerManagementProject.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long>, JpaSpecificationExecutor<Customers> {

    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);
}
