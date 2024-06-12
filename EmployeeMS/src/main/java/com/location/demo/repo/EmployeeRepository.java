package com.location.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.location.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

}
