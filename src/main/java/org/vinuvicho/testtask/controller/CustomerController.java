package org.vinuvicho.testtask.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinuvicho.testtask.dto.CustomerDTO;
import org.vinuvicho.testtask.entity.Customer;
import org.vinuvicho.testtask.mapper.CustomerToCustomerDTO;
import org.vinuvicho.testtask.service.CustomerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }

    @PutMapping("/customer/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}
