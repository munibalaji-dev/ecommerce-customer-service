package com.munibalaji.CustomerManagementProject.repositories.specifications;

import com.munibalaji.CustomerManagementProject.models.Customers;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<Customers> hasName(String name){
//            return (root, query, criteriaBuilder) ->
//                    name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("customerName")), "%" +name.toLowerCase()+ "%");

        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) return null;

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("customerName")),
                    "%" + name.toLowerCase() + "%"
            );

        };
    }

    public static Specification<Customers> hasEmail(String email){
//        return (root, query, criteriaBuilder) ->
//                email == null ? null : criteriaBuilder.equal(root.get("email"), email);

        return (root, query, criteriaBuilder) -> {
            if (email == null || email.trim().isEmpty()) return null;

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")),
                    "%" +email.toLowerCase()+ "%"
            );
        };
    }

    public static Specification<Customers> hasAdress(String address){
//        return (root, query, criteriaBuilder) ->
//                address == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" +address.toLowerCase()+ "%");

        return (root, query, criteriaBuilder) -> {
            if (address == null || address.trim().isEmpty()) return null;

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("address")),
                    "%" + address.toLowerCase() + "%"
            );

        };
    }
}
