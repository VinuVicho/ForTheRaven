package org.vinuvicho.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vinuvicho.testtask.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> getCustomerById(Long id);

    Optional<Customer> getCustomerByEmail(String email);

    @Modifying
    @Query(value = "update customer set full_name = ?1, updated = ?3 where id = ?2", nativeQuery = true)
    void updateCustomerName(String name, Long id, LocalDateTime time);

    @Modifying
    @Query(value = "update customer set phone = ?1, updated = ?3 where id = ?2", nativeQuery = true)
    void updateCustomerPhone(String phone, Long id, LocalDateTime time);

    @Modifying
    @Query(value = "update customer set full_name = ?1, phone = ?2, updated = ?4 where id = ?3", nativeQuery = true)
    void updateCustomer(String name, String phone, Long id, LocalDateTime time);


    Optional<Customer> getCustomerByIdAndIsActiveTrue(Long id);

    List<Customer> getAllByIsActiveTrue();

    @Modifying
    @Query(value = "update customer set is_active = false where id = ?1", nativeQuery = true)
    void deleteCustomer(Long id);
}
