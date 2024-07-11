package com.example.crud.service;

import com.example.crud.entity.Address;
import com.example.crud.entity.Employee;
//import com.example.crud.entity.Phone;
import com.example.crud.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        if (employee.getLaptop() != null) {
            employee.getLaptop().setEmployee(employee);
        }
        if (employee.getAddresses() != null){
            for (Address address : employee.getAddresses().values()){
                address.setEmployee(employee);
            }

        }

        return employeeRepository.save(employee);
    }

 //     if (employee.getPhones() != null) {
//            for (Phone phone : employee.getPhones()) {
//                phone.setEmployee(employee);
//            }
//        }

    @Override
    public List<Employee> fetchAllEmployees() {
       // List<Employee> allEmployees = employeeRepository.findAll();  //inline variable.
        return employeeRepository.findAll();
    }

//    @Override
//    public Employee getEmployeeById(Long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()) {
//            log.info("employee details: {} ", employee.get());
//            return employee.get();
//        } else {
//            throw new RuntimeException("Employee not find for id" + id);
//        }
//    }



    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String employeeJson = mapper.writeValueAsString(employee.get());
                log.info("employee details: {}", employeeJson);
            } catch (Exception e) {
                log.error("Error converting employee to JSON", e);
            }
            return employee.get();
        } else {
            throw new RuntimeException("Employee not found for id " + id);
        }
    }


    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
           employee.setEmployeeId(id);
           employee.setCreatedAt(emp.get().getCreatedAt());
            if (Objects.isNull(employee.getEmployeeName()) || StringUtils.isBlank(employee.getEmployeeName())) {
                employee.setEmployeeName(emp.get().getEmployeeName());
            }
            if (Objects.nonNull(employee.getEmployeeSalary()) && employee.getEmployeeSalary() <= 0.0f) {
                employee.setEmployeeSalary(emp.get().getEmployeeSalary());
            }
            if (Objects.isNull(employee.getEmployeeDesignation()) || StringUtils.isBlank(employee.getEmployeeDesignation())) {
                employee.setEmployeeName(emp.get().getEmployeeDesignation());
            }
            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException("account not found");
        }
    }

//    @Override
//    public Employee deleteEmployeeById(Long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()) {
//            employeeRepository.deleteById(id);
//            return employee.get();
//        } else {
//              throw new RuntimeException("account not found");
//        }

    public String deleteEmployeeById(Long id) {

        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return ("employee account deleted with id:" +id);
        } else {
            throw new RuntimeException("account not found");
        }}

    public Employee getEmployeeDetailsByCreatedAt(Timestamp createdAt){
        return employeeRepository.getEmployeeDetailsByCreatedAt(createdAt);
    }
    public Employee getEmployeeDetailsByDate(Timestamp createdAt) {
        System.out.println("Service: Searching for employee created at: " + createdAt);
        //Employee employee = employeeRepository.getEmployeeDetailsByDate(createdAt);
        return employeeRepository.getEmployeeDetailsByDate(createdAt);
//        System.out.println("Service: Found employee: " + employee);
//        return employee;
    }

    public Employee getEmployeeDetailsBySalary(Float employeeSalary) {
        return employeeRepository.getEmployeeDetailsBySalary(employeeSalary);
    }

    public List <Employee> searchEmployee(String query){
        ArrayList arrayList = new ArrayList();
      //   employeeRepository.searchEmployee(query);
        return  arrayList;
    }


}