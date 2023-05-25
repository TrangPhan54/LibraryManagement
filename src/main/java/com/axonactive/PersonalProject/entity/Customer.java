package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_Id")
    private Long customerID;
    @Column (name ="customer_firstname")
    private String customerFirstName;
    @Column (name = "customer_lastname")
    private String customerLastName;
    @Column (name ="customer_address")
    private String customerAddress;
    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;
    @Column (name = "customerEmail", length = 500)
    private String customerEmail;
    @Column (name = "customerPassword", length = 1000)
    private String customerPassword;
    @Column (name = "roleName")
    @Enumerated(EnumType.STRING)
    private Role roleName;


}
