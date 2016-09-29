package com.edvantis.workopolis.webservices;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.util.ListToJSONConverter;

@RestController
@RequestMapping("/api")
public class RestServiceImpl implements RestService {

	@Autowired
	private VacancyDAO vacancyDAO;

	@Autowired
	private CandidateDAO candidateDAO;

	@RequestMapping(value = "/vacancies", method = RequestMethod.GET)
	public ResponseEntity<String> getVacancies(String keyword, Integer salary,
			Direction direction, String address, Integer offset,
			Integer maxResults) {
		try {
			List<Vacancy> vacancies = vacancyDAO.getVacancies(keyword, salary,
					direction, address, offset, maxResults);
			return new ResponseEntity<String>(ListToJSONConverter
					.getVacanciesInJSON(vacancies).toString(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
	}

	@RequestMapping(value = "/vacancy/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getVacancyById(@PathVariable Integer id) {
		try {
			Vacancy vacancy = vacancyDAO.getVacancyById(id);
			return new ResponseEntity<String>(ListToJSONConverter
					.getVacancyInJSON(vacancy).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.GONE);
		}
	}

	@RequestMapping("/login")
	public ResponseEntity<String> user(Principal user) {
		try {
			Candidate androidUser = candidateDAO.getCandidateByEmail(user
					.getName());
			return new ResponseEntity<String>(ListToJSONConverter
					.getCandidateInJSON(androidUser).toString(),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response,
						auth);
				return new ResponseEntity(null, HttpStatus.I_AM_A_TEAPOT);
			}
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public HttpStatus apply(@RequestParam("id") Integer vacancyId) {

		try {
			if (SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities().toString().equals("[ROLE_CANDIDATE]")) {

				String candidateEmail = SecurityContextHolder.getContext()
						.getAuthentication().getName();
				Candidate candidate = candidateDAO
						.getCandidateByEmail(candidateEmail);
				candidateDAO.applyVacancy(candidate.getId(), vacancyId);
				return HttpStatus.OK;
			} else
				return HttpStatus.UNAUTHORIZED;
		} catch (Error e) {
			return HttpStatus.EXPECTATION_FAILED;
		}

	}

	@RequestMapping(value = "/check_apply", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkForApply(@RequestParam("id") Integer vacancyId) {
		try {
			if (SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities().toString().equals("[ROLE_CANDIDATE]")) {

				String candidateEmail = SecurityContextHolder.getContext()
						.getAuthentication().getName();
				Candidate candidate = candidateDAO
						.getCandidateByEmail(candidateEmail);
				System.err.println(check(vacancyId, candidate));
				return new ResponseEntity<Boolean>(check(vacancyId, candidate), HttpStatus.OK);
			} else
				return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
		} catch (Error e) {
			return new ResponseEntity<Boolean>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	private Boolean check(Integer vacancyId, Candidate candidate) {
		Vacancy vacancy = vacancyDAO.getVacancyById(vacancyId);
		Set<Candidate> appliedCandidates = vacancy.getAppliedCandidates();
		for (Candidate i:appliedCandidates){
			if (i.getEmail().equals(candidate.getEmail())) return true;
		}
		return false;
	}

}