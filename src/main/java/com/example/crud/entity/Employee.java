package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", updatable = false, nullable = false)

    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_designation")
    private String employeeDesignation;

    @Column(name = "employee_salary")
    private Float employeeSalary;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

//    @ElementCollection
//    @CollectionTable(name = "employee_address_mapping", joinColumns = @JoinColumn(name = "employee_id"))
//    @MapKeyEnumerated(EnumType.STRING)
//    @MapKeyColumn(name = "address_type")
//    @Column(name = "address_id")
//    private Map<AddressType, Long> addressMap = new HashMap<>();
//

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "addressType")
    @JsonManagedReference    // Breaks the loop when serializing to JSON
    private Map<AddressType, Address> addresses;


    //  @ToString.Exclude          // @ToString.Exclude annotation instructs Lombok to skip including that field
    // in the generated toString() method automatically.

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
   private Laptop laptop;     //foreign key will be created in Laptop table only


    @Override
    public String toString() {             //Employee toString(): Include laptop details with a null check to avoid NullPointerException.
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeDesignation='" + employeeDesignation + '\'' +
                ", employeeSalary=" + employeeSalary +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", addresses=" + (addresses != null ? "size: " + addresses.size() : null) +
              //  ", laptop=" + laptop +  // will print "laptop":null
                ", laptop=" + (laptop != null ? laptop.getBrand() + " " + laptop.getModelNo() : "null") +
                '}';

    }
}



// Other code...

//    if (employee.getPhones() != null && employee.getPhones().size() > 0) {
//        for (Phone phone : employee.getPhones()) {
//        phone.setEmployee(employee);
//        }

//public void setLaptop(Laptop laptop) {
//        this.laptop = laptop;
//        if (laptop != null) {
//            laptop.setEmployee(this);
//        }
//@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
//    private List<Phone> phones = new ArrayList<>();
//
//    public void addPhone(Phone phone) {
//        phones.add(phone);
//        phone.setEmployee(this);
//    }