package com.api.ressourceshumaines.apirh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.ressourceshumaines.apirh.model.Employe;

@Repository
public interface EmployeRepository extends CrudRepository<Employe, Long> {

}
