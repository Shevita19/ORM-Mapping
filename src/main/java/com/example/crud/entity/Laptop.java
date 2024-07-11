package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table ( name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long laptopId;
    private String modelNo;
    private String brand;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @Override
    public String toString() {     //Laptop toString(): Exclude employee to avoid circular references.
        return "Laptop{" +
                "laptopId=" + laptopId +
                ", modelNo='" + modelNo + '\'' +
                ", brand='" + brand + '\'' +
                // Exclude employee to avoid circular reference
                // ", employee=" + employee +
                '}';
    }


}
