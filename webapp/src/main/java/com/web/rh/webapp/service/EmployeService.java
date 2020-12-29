package com.web.rh.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.rh.webapp.model.Employe;
import com.web.rh.webapp.repository.EmployeProxy;
import lombok.Data;

@Service
public class EmployeService {

    @Autowired
    private EmployeProxy employeProxy;

    public Employe getEmploye(final int id) {
        return employeProxy.getEmploye(id);
    }

    public Iterable<Employe> getEmployes() {
        return employeProxy.getEmployes();
    }

    public void deleteEmploye(final int id) {
        employeProxy.deleteEmploye(id);;
    }

     public Employe saveEmploye(Employe employe) {
        Employe savedEmploye;

        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        employe.setLastName(employe.getLastName().toUpperCase());

        if(employe.getId() == null) {
            // Si l'id est nul, alors c'est un nouvel employé.
            savedEmploye = employeProxy.createEmploye(employe);
        } else {
            savedEmploye = employeProxy.updateEmploye(employe);
        }
    
        return savedEmploye;
    }

}