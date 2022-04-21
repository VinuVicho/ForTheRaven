package org.vinuvicho.testtask.mapper;

import org.springframework.stereotype.Component;
import org.vinuvicho.testtask.dto.CustomerDTO;
import org.vinuvicho.testtask.entity.Customer;

@Component
public class CustomerToCustomerDTO {
    public Customer toEntity(CustomerDTO c) {
        return new Customer(c.getId(), c.getFullName(), c.getEmail(), c.getPhone());
    }

    public CustomerDTO toDTO(Customer c) {
        return new CustomerDTO(c.getId(), c.getFullName(), c.getEmail(), c.getPhone());
    }
}
