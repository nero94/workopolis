package com.edvantis.workopolis.webservices;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public interface RestService {
 
	public  ResponseEntity<String> getVacancies(String keyword, Integer salary,
			Direction direction, String address, Integer offset,
			Integer maxResults);
	
	public ResponseEntity<String> getVacancyById(Integer id);
	
	public ResponseEntity<String> user(Principal user);
	
	public ResponseEntity logoutPage(HttpServletRequest request, HttpServletResponse response);
	
	public HttpStatus apply(Integer id);
	
}
