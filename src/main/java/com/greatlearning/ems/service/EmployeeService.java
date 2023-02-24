package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.repository.EmployeeRepository;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();
    Employee getEmployeeById(Integer id);
    Employee createNewEmployee(Employee employee);
    Employee updateEmployee(Integer id, Employee employee);
    String deleteEmployee(Integer id) throws Exception;

}
