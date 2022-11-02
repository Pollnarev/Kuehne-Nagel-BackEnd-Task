package com.Gruge.MyBackend.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "registration_code", nullable = false)
    private String registrationCode;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "phone", nullable = false)
    @Pattern(regexp="\\(?\\+[0-9]{1,3}\\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})? ?(\\w{1,10}\\s?\\d{1,6})?",
            message = "Provided phone number is not valid")
    private String phone;
}
