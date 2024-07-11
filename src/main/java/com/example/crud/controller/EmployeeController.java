package com.example.crud.controller;

import com.example.crud.entity.Employee;
import com.example.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

//    @PostMapping("/employee")
//    private Employee saveEmployee(@RequestBody Employee employee) {
//        return employeeService.saveEmployee(employee);
//    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getEmployees")
    private List<Employee> getAllEmployees() {
        return employeeService.fetchAllEmployees();
    }

    @GetMapping("/employee/{id}")
    private Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/updateEmployees/{id}")
    private Employee updateEmployeeById(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    private String deleteEmployeeById(@PathVariable("id") Long id) {
        return employeeService.deleteEmployeeById(id);
        //return employeeService.getEmployeeById(id);
    }

//    @GetMapping("/getEmployee")
//    public Employee getEmployeeDetailsByCreatedAt(@RequestParam("createdAt") Timestamp createdAt) {
//        return employeeService.getEmployeeDetailsByCreatedAt(createdAt);
//    }

    @GetMapping("/getEmployee")
    public Employee getEmployeeDetailsByCreatedDate(
            @RequestParam("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt) {
        Timestamp timestamp = Timestamp.valueOf(createdAt);
        return employeeService.getEmployeeDetailsByCreatedAt(timestamp);
    }


    @GetMapping("/getEmployeeByDate")
    public Employee getEmployeeDetailsByDate(
            @RequestParam("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt) {
        Timestamp startOfDay = Timestamp.valueOf(createdAt.atStartOfDay());
        return employeeService.getEmployeeDetailsByDate(startOfDay);
    }



//    @GetMapping("/getEmployeeByDate")
//    public Employee getEmployeeDetailsByDate(
//            @RequestParam("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt) {
//        Timestamp startOfDay = Timestamp.valueOf(createdAt.atStartOfDay());
//        System.out.println("Searching for employee created at: " + startOfDay);
//        Employee employee = employeeService.getEmployeeDetailsByDate(startOfDay);
//        System.out.println("Found employee: " + employee);
//        return employee;
//    }

    @GetMapping("/getEmployeeBySalary")
    public Employee getEmployeeBySalary(@RequestParam Float employeeSalary) {
        return employeeService.getEmployeeDetailsBySalary(employeeSalary);
    }

//    @GetMapping("/searchEmployee")
//    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam("query") String query){
//        return ResponseEntity.ok(employeeService.searchEmployee(query));
//    }

//    @GetMapping("/searchEmployee")
//    private List<Employee> searchEmployee(String query) {
//        return employeeService.searchEmployee(query);
//    }
@GetMapping("/searchEmployee")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam("query") String query){
        List<Employee> employees = employeeService.searchEmployee(query);
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(employees);
        }
        return ResponseEntity.ok(employees);
    }
}
