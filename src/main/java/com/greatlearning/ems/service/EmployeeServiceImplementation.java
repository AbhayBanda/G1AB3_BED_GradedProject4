package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee createNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(!optionalEmployee.isPresent())
            return new Employee();
        Employee oldEmployee = optionalEmployee.get();
        if(employee.getFirstName() != null)
            oldEmployee.setFirstName(employee.getFirstName());
        if(employee.getLastName() != null)
            oldEmployee.setLastName(employee.getLastName());
        if(employee.getEmail() != null)
            oldEmployee.setEmail(employee.getEmail());

        Employee updatedEmployee = oldEmployee;

        employeeRepository.save(updatedEmployee);

        return updatedEmployee;
    }

    @Override
    public String deleteEmployee(Integer id) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if(!optionalEmployee.isPresent())
            throw new Exception("OOPS!! employee not present");

        Employee employeeToDelete = optionalEmployee.get();
        employeeRepository.delete(employeeToDelete);
        return String.format("Deleted employee id - %d", employeeToDelete.getId());
    }

    public List<Employee> getEmployeeByFirstName(String firstName) {

        return employeeRepository.findByFirstName(firstName);
    }
    public List<Employee> getSortedEmployeesBasedOnFirstName(String order) {
        return order.equalsIgnoreCase("asc")
                ? employeeRepository.findByOrderByFirstNameAsc()
                : employeeRepository.findByOrderByFirstNameDesc();
    }
}
