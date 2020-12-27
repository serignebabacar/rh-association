package com.web.rh.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.rh.webapp.model.Employe;
import com.web.rh.webapp.service.EmployeService;
@Controller
public class EmployeController {
	@Autowired
	private EmployeService service;
	
	@GetMapping("/")
	public String home(Model model) {
		Iterable<Employe> listEmploye = service.getEmployes();
		model.addAttribute("employees", listEmploye);
		return "home";
	}
	
	@GetMapping("/createEmployee")
	public String createEmploye(Model model) {
		Employe e = new Employe();
		model.addAttribute("employee", e);
		return "formNewEmployee";
	}
	
	@GetMapping("/updateEmployee/{id}")
	public String updateEmploye(@PathVariable("id") final int id, Model model) {
		Employe e = service.getEmploye(id);		
		model.addAttribute("employee", e);	
		return "formUpdateEmployee";		
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmploye(@PathVariable("id") final int id) {
		service.deleteEmploye(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/saveEmployee")
	public ModelAndView saveEmploye(@ModelAttribute Employe employe) {
		if(employe.getId() != null) {
			Employe current = service.getEmploye(employe.getId());
			employe.setPassword(current.getPassword());
		}
		service.saveEmploye(employe);
		return new ModelAndView("redirect:/");	
	}
}
