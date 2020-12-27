package com.web.rh.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.web.rh.webapp.CustomProperties;
import com.web.rh.webapp.model.Employe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeProxy {

	@Autowired
	private CustomProperties props;

	/**
	 * Get all Employes
	 * 
	 * @return An iterable of all Employes
	 */
	public Iterable<Employe> getEmployes() {

		String baseApiUrl = props.getApiUrl();
		String getEmployesUrl = baseApiUrl + "/Employes";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Employe>> response = restTemplate.exchange(getEmployesUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<Employe>>() {
				});


		return response.getBody();
	}

	/**
	 * Get an Employe by the id
	 * 
	 * @param id The id of the Employe
	 * @return The Employe which matches the id
	 */
	public Employe getEmploye(int id) {
		String baseApiUrl = props.getApiUrl();
		String getEmployeUrl = baseApiUrl + "/Employe/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employe> response = restTemplate.exchange(getEmployeUrl, HttpMethod.GET, null, Employe.class);

		return response.getBody();
	}

	/**
	 * Add a new Employe
	 * 
	 * @param e A new Employe (without an id)
	 * @return The Employe full filled (with an id)
	 */
	public Employe createEmploye(Employe e) {
		String baseApiUrl = props.getApiUrl();
		String createEmployeUrl = baseApiUrl + "/Employe";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employe> request = new HttpEntity<Employe>(e);
		ResponseEntity<Employe> response = restTemplate.exchange(createEmployeUrl, HttpMethod.POST, request,
				Employe.class);

		return response.getBody();
	}

	/**
	 * Update an Employe - using the PUT HTTP Method.
	 * 
	 * @param e Existing Employe to update
	 */
	public Employe updateEmploye(Employe e) {
		String baseApiUrl = props.getApiUrl();
		String updateEmployeUrl = baseApiUrl + "/Employe/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employe> request = new HttpEntity<Employe>(e);
		ResponseEntity<Employe> response = restTemplate.exchange(updateEmployeUrl, HttpMethod.PUT, request,
				Employe.class);

		return response.getBody();
	}

	/**
	 * Delete an Employe using exchange method of RestTemplate instead of delete
	 * method in order to log the response status code.
	 * 
	 * @param e The Employe to delete
	 */
	public void deleteEmploye(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteEmployeUrl = baseApiUrl + "/Employe/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(deleteEmployeUrl, HttpMethod.DELETE, null, Void.class);

	}

}