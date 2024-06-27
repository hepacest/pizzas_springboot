package com.platzi.pizzeriaDominos.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="customer")
@NoArgsConstructor
@Getter
@Setter
public class CustomerEntity {

    @Id
    @Column (name="id_customer", length = 15, nullable = false)
    private Integer idCustomer;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(length = 100)
    private String address;

    @Column( nullable = false, length = 50, unique = true)
    private String email;

    @Column (name = "phone_number", length = 20)
    private String phoneNumber;

}
