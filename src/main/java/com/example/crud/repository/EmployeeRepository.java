package com.example.crud.repository;

import com.example.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query(value = "SELECT * FROM employee WHERE created_at = :createdAt", nativeQuery = true)
    Employee getEmployeeDetailsByCreatedAt(@Param("createdAt") Timestamp createdAt);


        // Use a function to compare only the date part
        @Query(value = "SELECT * FROM employee WHERE DATE(created_at) = DATE(:createdAt)", nativeQuery = true)
        Employee getEmployeeDetailsByDate(@Param("createdAt") Timestamp createdAt);

        @Query(value = "SELECT * FROM employee WHERE employee_salary = :employeeSalary", nativeQuery = true)
        Employee getEmployeeDetailsBySalary(@Param("employeeSalary") Float employeeSalary);

        @Query (value = "SELECT * from employee e WHERE "
                + "e.employee_name LIKE CONCAT ('%', :query, '%')"
                + "Or e.employee_designation LIKE CONCAT ('%', :query, '%')", nativeQuery= true)
        List<Employee> searchEmployee(String query);



	/* @Query(value= "SELECT * from products p WHERE"+
	"p.name LIKE CONCAT ('%', :query, '%')" +
	"Or p.description LIKE CONCAT ('%', :query,'%')", nativeQuery=true) */

        }

//@Query("SELECT e FROM Employee e WHERE e.salary = :salary")
//Employee getEmployeeDetailsBySalary(@Param("salary") Float salary);
//}

    // List<Employee> dateCreatedBetween(Timestamp createdAt, Timestamp updatedAt){

