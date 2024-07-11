package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Embeddable  //An entity class is an ordinary user defined Java class whose instances can be stored in the database.
                // Embeddable classes are user defined persistable classes that function as value types
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Embeddable
@Table(name = "address")  // Optional: You can specify table name if needed
public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "address_id", updatable = false, nullable = false)
        private Long addressId;

        @Column(name = "street")
        private String street;

        @Column(name = "city")
        private String city;

        @Column(name = "country")
        private String country;

        @Column(name = "zip_code")
        private int zipCode;

        @Enumerated(EnumType.STRING)
        @Column(name = "address_type")
        private AddressType addressType;

        
        @ManyToOne
        @JoinColumn(name = "employee_id", nullable = false)
        @JsonBackReference
        private Employee employee;
        }

