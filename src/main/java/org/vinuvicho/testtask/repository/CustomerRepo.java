package org.vinuvicho.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vinuvicho.testtask.entity.Customer;

import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Long> {


    Optional<Customer> getCustomerById(Long id);

    @Modifying
    @Query(value = "update customer set full_name = ?1, phone = ?2 where id = ?3", nativeQuery = true)
    void updateCustomer(String name, String phone, Long id);

    @Modifying
    @Query(value = "update customer set is_active = false where id = ?1", nativeQuery = true)
    void deleteCustomer(Long id);
}
