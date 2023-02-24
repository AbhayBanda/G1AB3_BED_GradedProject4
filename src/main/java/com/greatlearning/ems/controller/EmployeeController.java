package com.greatlearning.ems.controller;


import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.service.EmployeeServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImplementation employeeServiceImplementation;

    @GetMapping("/employees")
    List<Employee> getAllEmployees() {
        System.out.println(employeeServiceImplementation.getEmployeeList());
        return employeeServiceImplementation.getEmployeeList();
    }

    @GetMapping("/employees/{id}")
    Employee getEmployeeById(@PathVariable Integer id) {
        return employeeServiceImplementation.getEmployeeById(id);
    }

    @GetMapping("/employees/search/{firstName}")
    List<Employee> searchEmployeeByFirstName(@PathVariable String firstName) {

        return employeeServiceImplementation.getEmployeeByFirstName(firstName);
    }

    @GetMapping("/employees/sort")
    List<Employee> sortEmployeesByFirstName(
            @RequestParam("order") String order
    ) {
        return employeeServiceImplementation.getSortedEmployeesBasedOnFirstName(order);
    }

    @PostMapping("/employees/add")
    Employee addEmployee(@RequestBody Employee employee) {
        return employeeServiceImplementation.createNewEmployee(employee);
    }

    @PutMapping("/employees/update/{id}")
    Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeServiceImplementation.updateEmployee(id, employee);
    }

    @DeleteMapping("/employees/delete/{id}")
    String deleteEmployee(@PathVariable Integer id) throws Exception {
        return employeeServiceImplementation.deleteEmployee(id);
    }

}
