package com.location.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.location.demo.excpetion.ResourceNotFoundException;
import com.location.demo.model.Employee;
import com.location.demo.repo.EmployeeRepository;

@CrossOrigin(origins = {})
@RestController
@RequestMapping("/employee")
public class EmployeeController 
{
	@Autowired
    private EmployeeRepository employeeRepository;
    
	@GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployee()
    {
    	return employeeRepository.findAll();
    }
	
	@PostMapping("/saveEmployee")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
	  Employee employee = employeeRepository.findById(id)
	    .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with Id: " + id));
	  return ResponseEntity.ok(employee);
	}
	
	
	
	@PutMapping("updateEmployeeById/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id,@RequestBody Employee employeeDetails)
	{
		Employee updateEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with Id: "+ id));
		
		updateEmployee.setFirstName(employeeDetails.getFirstName());
		updateEmployee.setLastName(employeeDetails.getLastName());
		updateEmployee.setEmailId(employeeDetails.getEmailId());
		
		employeeRepository.save(updateEmployee);
		
		return ResponseEntity.ok(updateEmployee);

	}
	
	@DeleteMapping("deleteEmployeeById/{id}")
	public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable long id)
	{
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with Id: "+ id));
		
		employeeRepository.delete(employee);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
