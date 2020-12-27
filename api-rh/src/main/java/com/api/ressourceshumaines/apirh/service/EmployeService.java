package com.api.ressourceshumaines.apirh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ressourceshumaines.apirh.model.Employe;
import com.api.ressourceshumaines.apirh.repository.EmployeRepository;

import lombok.Data;
@Data
@Service
public class EmployeService {
	@Autowired
	private EmployeRepository employeRepository;

	public Optional<Employe> getEmploye(final Long id) {
		return employeRepository.findById(id);
	}
	public Iterable<Employe> getEmployes(){
	return 	employeRepository.findAll();
	}
	public void deleteEmploye(final Long id) {
		employeRepository.deleteById(id);
	}
	public Employe saveEmploye(Employe em) {
		return  employeRepository.save(em);
	}
}
