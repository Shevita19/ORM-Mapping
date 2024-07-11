//package com.example.crud.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "phone")
//public class Phone {
//
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @Column(name = "phone_id")
//        private Long id;
//
//        @Column(name = "phone_number")
//        private String phoneNumber;
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "employee_id")
//        private Employee employee;
//
//    }
//
