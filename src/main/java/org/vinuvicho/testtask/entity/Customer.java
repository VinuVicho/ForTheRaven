package org.vinuvicho.testtask.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Customer {
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated;
    private String phone;
    private Boolean isActive = true;

    public Customer() {
    }

    public Customer(Long id, String fullName, String email, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
}
