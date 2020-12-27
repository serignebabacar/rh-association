package com.web.rh.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.rh.webapp.model.Employe;
import com.web.rh.webapp.repository.EmployeProxy;
import lombok.Data;

@Data
@Service
public class EmployeService {

    @Autowired
    private EmployeProxy EmployeProxy;

    public Employe getEmploye(final int id) {
        return EmployeProxy.getEmploye(id);
    }

    public Iterable<Employe> getEmployes() {
        return EmployeProxy.getEmployes();
    }

    public void deleteEmploye(final int id) {
        EmployeProxy.deleteEmploye(id);;
    }

     public Employe saveEmploye(Employe employe) {
        Employe savedEmploye;

        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        employe.setLastName(employe.getLastName().toUpperCase());

        if(employe.getId() == null) {
            // Si l'id est nul, alors c'est un nouvel employé.
            savedEmploye = EmployeProxy.createEmploye(employe);
        } else {
            savedEmploye = EmployeProxy.updateEmploye(employe);
        }
    
        return savedEmploye;
    }

}