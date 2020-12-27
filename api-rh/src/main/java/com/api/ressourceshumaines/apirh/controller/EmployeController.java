package com.api.ressourceshumaines.apirh.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ressourceshumaines.apirh.model.Employe;
import com.api.ressourceshumaines.apirh.service.EmployeService;


@RestController
public class EmployeController {
	
	@Autowired
	private EmployeService employeeService;
	
	/**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/employee")
	public void createEmployee(@RequestBody Employe employee) {
		 employeeService.saveEmploye(employee);
	}
	
	
	/**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	@GetMapping("/employee/{id}")
	public Employe getEmployee(@PathVariable("id") final Long id) {
		Optional<Employe> employee = employeeService.getEmploye(id);
		if(employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all employees
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/employees")
	public Iterable<Employe> getEmployees() {
		return employeeService.getEmployes();
	}
	
	/**
	 * Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/employee/{id}")
	public Employe updateEmployee(@PathVariable("id") final Long id, @RequestBody Employe employee) {
		Optional<Employe> e = employeeService.getEmploye(id);
		if(e.isPresent()) {
			Employe currentEmployee = e.get();
			
			String firstName = employee.getFirstName();
			if(firstName != null) {
				currentEmployee.setFirstName(firstName);
			}
			String lastName = employee.getLastName();
			if(lastName != null) {
				currentEmployee.setLastName(lastName);;
			}
			String mail = employee.getMail();
			if(mail != null) {
				currentEmployee.setMail(mail);
			}
			String password = employee.getPassword();
			if(password != null) {
				currentEmployee.setPassword(password);;
			}
			employeeService.saveEmploye(currentEmployee);
			return currentEmployee;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") final Long id) {
		employeeService.deleteEmploye(id);
	}
	

}