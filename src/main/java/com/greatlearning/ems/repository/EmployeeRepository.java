package com.greatlearning.ems.repository;


import com.greatlearning.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstName(String username);
    List<Employee> findByOrderByFirstNameAsc();
    List<Employee> findByOrderByFirstNameDesc();
}
