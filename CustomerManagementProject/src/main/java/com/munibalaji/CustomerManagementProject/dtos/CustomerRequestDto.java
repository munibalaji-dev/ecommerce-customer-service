package com.munibalaji.CustomerManagementProject.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {

    @NotBlank(message = "Customer name cannot null")
    private String customerName;

    @Column(unique = true, nullable = false)
    @Email(message = "Please provide a valid email")
    private String email;


    @Column(unique = true, nullable = false)
    @Min(value = 1000000000L, message = "Phone number must be 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be 10 digits")
    private Long phone;

    private String address;
}
