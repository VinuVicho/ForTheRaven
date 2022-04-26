package org.vinuvicho.testtask.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vinuvicho.testtask.dto.CustomerDTO;
import org.vinuvicho.testtask.service.CustomerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping()
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping()
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(customer);
    }

    @PatchMapping("/{id}")
    public CustomerDTO updateCustomerField(@RequestBody CustomerDTO customer) {
        return customerService.updateCustomerField(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}
