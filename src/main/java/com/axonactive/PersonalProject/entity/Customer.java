package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column (name ="first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name ="address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column (name = "email", length = 500)
    private String email;
    @Column (name = "password", length = 1000)
    private String password;
    private Long numberOfTimeReturnLate;
    @Column (name = "active")
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Role role;


}
