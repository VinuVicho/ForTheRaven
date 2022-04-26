package org.vinuvicho.testtask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.vinuvicho.testtask.dto.CustomerDTO;
import org.vinuvicho.testtask.exeption.InvalidInputException;
import org.vinuvicho.testtask.exeption.NotFoundException;
import org.vinuvicho.testtask.mapper.CustomerToCustomerDTO;
import org.vinuvicho.testtask.repository.CustomerRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private CustomerToCustomerDTO customerMapper;

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getId() == null && customerRepo.getCustomerById(customerDTO.getId()).isEmpty())
            throw new NotFoundException();
        if (!validateFullName(customerDTO.getFullName()) || !validatePhone(customerDTO.getPhone()))
            throw new InvalidInputException();
        customerRepo.updateCustomer(customerDTO.getFullName(), customerDTO.getPhone(), customerDTO.getId(),
                LocalDateTime.now());
        return customerMapper.toDTO(customerRepo.getCustomerById(customerDTO.getId()).get());
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteCustomer(id);
    }

    public CustomerDTO getCustomer(Long id) {
        return customerMapper.toDTO(customerRepo.getCustomerByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new NotFoundException("Customer not found")));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.getAllByIsActiveTrue()
                .stream().map(c -> customerMapper.toDTO(c)).collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (validatePhone(customerDTO.getPhone())
                && validateEmail(customerDTO.getEmail())
                && validateFullName(customerDTO.getFullName()))
            return customerMapper.toDTO(customerRepo.save(customerMapper.toEntity(customerDTO)));
        else throw new InvalidInputException();
    }

    public boolean validatePhone(String phone) {
        if (phone == null) return true;
        if (phone.length() < 6 || phone.length() > 14) return false;
        return Pattern.compile("^\\+\\d{5,13}$").matcher(phone).matches();
    }

    public boolean validateFullName(String name) {
        return name.length() >= 2 && name.length() <= 50;
    }

    public boolean validateEmail(String email) {
        if (customerRepo.getCustomerByEmail(email).isPresent()) throw new InvalidInputException();
        if (email.length() < 2 || email.length() > 100) return false;
        return Pattern.compile("^(.+)@(\\S+)$")
                .matcher(email)
                .matches();
    }

    public CustomerDTO updateCustomerField(CustomerDTO customerDTO) {
        if (customerDTO.getId() == null && customerRepo.getCustomerById(customerDTO.getId()).isEmpty())
            throw new NotFoundException();
        if (customerDTO.getFullName() != null) {
            if (validateFullName(customerDTO.getFullName())) {
                customerRepo.updateCustomerName(customerDTO.getFullName(), customerDTO.getId(), LocalDateTime.now());
            } else throw new InvalidInputException();
        }
        if (customerDTO.getPhone() != null && validatePhone(customerDTO.getPhone())) {
            customerRepo.updateCustomerPhone(customerDTO.getPhone(), customerDTO.getId(), LocalDateTime.now());
        } else throw new InvalidInputException();
        return customerMapper.toDTO(customerRepo.getCustomerById(customerDTO.getId()).get());
    }
}
