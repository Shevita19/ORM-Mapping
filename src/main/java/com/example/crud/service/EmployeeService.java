package com.example.crud.service;

import com.example.crud.entity.Employee;

import java.sql.Timestamp;
import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> fetchAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployeeById(Long id, Employee employee);

    //Employee deleteEmployeeById(Long id);
    String deleteEmployeeById(Long id);

    Employee getEmployeeDetailsByCreatedAt (Timestamp createdAt);

    Employee getEmployeeDetailsByDate (Timestamp createdAt);

    Employee getEmployeeDetailsBySalary (Float employeeSalary);

    public List <Employee> searchEmployee(String query);



}
