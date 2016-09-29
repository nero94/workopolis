package com.edvantis.workopolis.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.edvantis.workopolis.model.Company;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.skill.CommunicationSkill;
import com.edvantis.workopolis.model.skill.TechnicalSkill;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.model.user.employer.Offer;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public class ListToJSONConverter {

	public static JSONObject getInterviewsInJSON(List <Interview> interviews ) throws JSONException{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < interviews.size(); i++){
			JSONObject additionalJSON = new JSONObject();
			try {
				additionalJSON.put("title", interviews.get(i).getVacancy().getTitle());
				additionalJSON.put("date", interviews.get(i).getDate());
				additionalJSON.put("interview", "Interview");
				additionalJSON.put("color", "#27a0c9");
				additionalJSON.put("interviewType", interviews.get(i).getInterviewType());
				additionalJSON.put("communicationType", interviews.get(i).getCommunicationType());
				additionalJSON.put("interviewerFirstName", interviews.get(i).getInterviewer().getFirstName());
				additionalJSON.put("interviewerLastName", interviews.get(i).getInterviewer().getLastName());
				additionalJSON.put("interviewerEmail", interviews.get(i).getInterviewer().getEmail());
				additionalJSON.put("candidateFirstName", interviews.get(i).getCandidate().getFirstName());
				additionalJSON.put("candidateLastName", interviews.get(i).getCandidate().getLastName());
				additionalJSON.put("candidateEmail", interviews.get(i).getCandidate().getEmail());
				additionalJSON.put("candidateTel", interviews.get(i).getCandidate().getTelNumber());
				additionalJSON.put("interviewerTel", interviews.get(i).getInterviewer().getTelNumber());
				} catch (NullPointerException e) {
					System.out.println(e);
				}
				jsonArray.put(additionalJSON);
			
		}
		jsonObject.put("interviews", jsonArray);
		return jsonObject;
	}
	
	public static JSONArray getVacanciesInJSON(List <Vacancy> vacancies ) throws JSONException{
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < vacancies.size(); i++){
			JSONObject additionalJSON = new JSONObject();
			try {
				
			 
				additionalJSON.put("id", vacancies.get(i).getId());
				additionalJSON.put("title", vacancies.get(i).getTitle());
				additionalJSON.put("description", vacancies.get(i).getDescription());
				Company company = vacancies.get(i).getCompany();
				additionalJSON.put("imgUrl", company.getImgUrl());
				additionalJSON.put("postDate", vacancies.get(i).getPostDate());	
			}
				catch (NullPointerException e) {
					System.out.println(e);
				}
				jsonArray.put(additionalJSON);
		}
		return jsonArray;
	}
	
	public static JSONArray getTechnicalSkillsInJSON(Set <TechnicalSkill> technicalSkills ) throws JSONException{
		JSONArray jsonArray = new JSONArray();
		
		for (TechnicalSkill skill:technicalSkills){
			JSONObject additionalJSON = new JSONObject();
				
				additionalJSON.put("id", skill.getId());
				additionalJSON.put("name", skill.getName());
				additionalJSON.put("level", skill.getLevel());
				additionalJSON.put("category", skill.getCategory());
				additionalJSON.put("importance", skill.getImportance());				
				jsonArray.put(additionalJSON);
		}
		return jsonArray;
	}
	
	public static JSONArray getCommunicationSkillsInJSON(Set <CommunicationSkill> communicationSkills ) throws JSONException{
		JSONArray jsonArray = new JSONArray();
		for (CommunicationSkill skill:communicationSkills){
			JSONObject additionalJSON = new JSONObject();
				
				additionalJSON.put("id", skill.getId());
				additionalJSON.put("name", skill.getName());
				additionalJSON.put("level", skill.getLevel());
				additionalJSON.put("importance", skill.getImportance());				
				jsonArray.put(additionalJSON);
		}
		return jsonArray;
	}
	
	public static JSONObject getVacancyInJSON(Vacancy vacancy ) throws JSONException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", vacancy.getId());
		jsonObject.put("title", vacancy.getTitle());
		jsonObject.put("direction", vacancy.getDirection());
		jsonObject.put("salary", vacancy.getSalary());
		jsonObject.put("address", vacancy.getCompany().getAddress().toString());
		jsonObject.put("responsibilities", vacancy.getResponsibilities());
		jsonObject.put("description", vacancy.getDescription());
		jsonObject.put("technicalSkills", getTechnicalSkillsInJSON(vacancy.getTechnicalSkills()));
		jsonObject.put("communicationSkills", getCommunicationSkillsInJSON(vacancy.getCommunicationSkills()));
		return jsonObject;
	}
	
	public static JSONObject getCandidateInJSON(Candidate candidate ) throws JSONException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", candidate.getId());
		jsonObject.put("email", candidate.getEmail());
		jsonObject.put("name", candidate.getFirstName());
		jsonObject.put("lastname", candidate.getLastName());
		return jsonObject;
	}
	
	public static JSONObject getOffersInJSON(List<Offer> offers) throws JSONException{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < offers.size(); i++) {
			JSONObject additionalJSON = new JSONObject();
			try {
			additionalJSON.put("title", offers.get(i).getVacancy().getTitle());
			additionalJSON.put("date", offers.get(i).getOfferDate());
			additionalJSON.put("color", "#30c36b");
			additionalJSON.put("offer", "Offer");
			additionalJSON.put("candidateFirstName", offers.get(i).getCandidate().getFirstName());
			additionalJSON.put("candidateLastName", offers.get(i).getCandidate().getLastName());
			additionalJSON.put("candidateEmail", offers.get(i).getCandidate().getEmail());
			additionalJSON.put("offerState", offers.get(i).getState());
			additionalJSON.put("offerComment", offers.get(i).getOfferComment());
			additionalJSON.put("candateTel", offers.get(i).getCandidate().getTelNumber());
			} catch (NullPointerException e) {
				System.out.println(e);
			}
			
			jsonArray.put(additionalJSON);
		}
		jsonObject.put("offers", jsonArray);
		return jsonObject;
	}
	
	public static JSONArray getVacanciesForStatistics(List<Vacancy> vacancies) throws JSONException{
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONObject jsonObject3 = new JSONObject();
		JSONObject jsonObject4 = new JSONObject();
		JSONObject jsonObject5 = new JSONObject();
		JSONObject jsonObject6 = new JSONObject();
		JSONObject jsonObject7 = new JSONObject();
		JSONObject jsonObject8 = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		int administationCount = 0;
		int businessAnalysisCount = 0;
		int designCount = 0;
		int databasesCount = 0;
		int projectManagementCount = 0;
		int qualityControlCount = 0;
		int softwareDevelopmentCount = 0;
		int systemArchitectureCount = 0;
		
		for (int i = 0; i < vacancies.size(); i++){
			if (vacancies.get(i).getDirection() == Direction.ADMINISTRATION) {
				administationCount ++;
			} else if (vacancies.get(i).getDirection() == Direction.BUSINESS_ANALYSIS) {
				businessAnalysisCount ++;
			} else if (vacancies.get(i).getDirection() == Direction.DESIGN) {
				designCount ++;
			} else if (vacancies.get(i).getDirection() == Direction.DATABASES) {
				databasesCount ++;
			} else if (vacancies.get(i).getDirection() == Direction.PROJECT_MANAGEMENT) {
				projectManagementCount ++;
			} else if (vacancies.get(i).getDirection() == Direction.QUALITY_CONTROL) {
				qualityControlCount ++;
			} else if (vacancies.get(i).getDirection() == Direction.SOFTWARE_DEVELOPMENT) {
				softwareDevelopmentCount ++;
			} else if (vacancies.get(i).getDirection() == Direction.SYSTEM_ARCHITECTURE) {
				systemArchitectureCount ++;
			}
		}
		jsonObject1.put("label", "Administration");
		jsonObject1.put("y", administationCount);
		
		jsonObject2.put("label", "Business Analysis");
		jsonObject2.put("y", businessAnalysisCount);
		
		jsonObject3.put("label", "Design");
		jsonObject3.put("y", designCount);
		
		jsonObject4.put("label", "Databases");
		jsonObject4.put("y", databasesCount);
		
		jsonObject5.put("label", "Project Management");
		jsonObject5.put("y", projectManagementCount);
		
		jsonObject6.put("label", "Quality Control");
		jsonObject6.put("y", qualityControlCount);
		
		jsonObject7.put("label", "Software Development");
		jsonObject7.put("y", softwareDevelopmentCount);
		
		jsonObject8.put("label", "Software Architecture");
		jsonObject8.put("y", systemArchitectureCount);
		
		jsonArray.put(jsonObject1);
		jsonArray.put(jsonObject2);
		jsonArray.put(jsonObject3);
		jsonArray.put(jsonObject4);
		jsonArray.put(jsonObject5);
		jsonArray.put(jsonObject6);
		jsonArray.put(jsonObject7);
		jsonArray.put(jsonObject8);
		
		return jsonArray;
	}
	
	public static JSONArray candidateRegiDates(List<Candidate> allUsers) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		JSONObject candidatesJan = new JSONObject();
		JSONObject candidatesFeb = new JSONObject();
		JSONObject candidatesMar = new JSONObject();
		JSONObject candidatesApr = new JSONObject();
		JSONObject candidatesMay = new JSONObject();
		JSONObject candidatesJun = new JSONObject();
		JSONObject candidatesJul = new JSONObject();
		JSONObject candidatesAug = new JSONObject();
		JSONObject candidatesSep = new JSONObject();
		JSONObject candidatesOct = new JSONObject();
		JSONObject candidatesNov = new JSONObject();
		JSONObject candidatesDec = new JSONObject();
		
		int candidateCountJan = 0;
		int candidateCountFeb = 0;
		int candidateCountMar = 0;
		int candidateCountApr = 0;
		int candidateCountMay = 0;
		int candidateCountJun = 0;
		int candidateCountJul = 0;
		int candidateCountAug = 0;
		int candidateCountSep = 0;
		int candidateCountOct = 0;
		int candidateCountNov = 0;
		int candidateCountDec = 0;
		
		
		
		
		for (Candidate candidate : allUsers) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(candidate.getRegistrationDate());
			int month = cal.get(Calendar.MONTH);
			if (month == 0) {
				candidateCountJan++;
			} else if (month == 1) {
				candidateCountFeb++;
			} else if (month == 2) {
				candidateCountMar++;
			} else if (month == 3) {
				candidateCountApr++;
			} else if (month == 4) {
				candidateCountMay++;
			} else if (month == 5) {
				candidateCountJun++;
			} else if (month == 6) {
				candidateCountJul++;
			} else if (month == 7) {
				candidateCountAug++;
			} else if (month == 8) {
				candidateCountSep++;
			} else if (month == 9) {
				candidateCountOct++;
			} else if (month == 10) {
				candidateCountNov++;
			} else if (month == 11) {
				candidateCountDec++;
			}
		}
		candidatesJan.put("label", "January");
		candidatesJan.put("y", candidateCountJan);
		
		candidatesFeb.put("label", "February");
		candidatesFeb.put("y", candidateCountFeb);
		
		candidatesMar.put("label", "March");
		candidatesMar.put("y", candidateCountMar);
		
		candidatesApr.put("label", "April");
		candidatesApr.put("y", candidateCountApr);
		
		candidatesMay.put("label", "May");
		candidatesMay.put("y", candidateCountMay);
		
		candidatesJun.put("label", "June");
		candidatesJun.put("y", candidateCountJun);
		
		candidatesJul.put("label", "July");
		candidatesJul.put("y", candidateCountJul);
		
		candidatesAug.put("label", "August");
		candidatesAug.put("y", candidateCountAug);
		
		candidatesSep.put("label", "September");
		candidatesSep.put("y", candidateCountSep);
		
		candidatesOct.put("label", "October");
		candidatesOct.put("y", candidateCountOct);
		
		candidatesNov.put("label", "November");
		candidatesNov.put("y", candidateCountNov);
		
		candidatesDec.put("label", "December");
		candidatesDec.put("y", candidateCountDec);
		
		jsonArray.put(candidatesJan);
		jsonArray.put(candidatesFeb);
		jsonArray.put(candidatesMar);
		jsonArray.put(candidatesApr);
		jsonArray.put(candidatesMay);
		jsonArray.put(candidatesJun);
		jsonArray.put(candidatesJul);
		jsonArray.put(candidatesAug);
		jsonArray.put(candidatesSep);
		jsonArray.put(candidatesOct);
		jsonArray.put(candidatesNov);
		jsonArray.put(candidatesDec);
		
		return jsonArray;
	}
	
	public static JSONArray employerRegiDates(List<Employer> allUsers) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		JSONObject employersJan = new JSONObject();
		JSONObject employersFeb = new JSONObject();
		JSONObject employersMar = new JSONObject();
		JSONObject employersApr = new JSONObject();
		JSONObject employersMay = new JSONObject();
		JSONObject employersJun = new JSONObject();
		JSONObject employersJul = new JSONObject();
		JSONObject employersAug = new JSONObject();
		JSONObject employersSep = new JSONObject();
		JSONObject employersOct = new JSONObject();
		JSONObject employersNov = new JSONObject();
		JSONObject employersDec = new JSONObject();
		
		int employerCountJan = 0;
		int employerCountFeb = 0;
		int employerCountMar = 0;
		int employerCountApr = 0;
		int employerCountMay = 0;
		int employerCountJun = 0;
		int employerCountJul = 0;
		int employerCountAug = 0;
		int employerCountSep = 0;
		int employerCountOct = 0;
		int employerCountNov = 0;
		int employerCountDec = 0;
		
		
		
		
		for (Employer employer : allUsers) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(employer.getRegistrationDate());
			int month = cal.get(Calendar.MONTH);
			if (month == 0) {
				employerCountJan++;
			} else if (month == 1) {
				employerCountFeb++;
			} else if (month == 2) {
				employerCountMar++;
			} else if (month == 3) {
				employerCountApr++;
			} else if (month == 4) {
				employerCountMay++;
			} else if (month == 5) {
				employerCountJun++;
			} else if (month == 6) {
				employerCountJul++;
			} else if (month == 7) {
				employerCountAug++;
			} else if (month == 8) {
				employerCountSep++;
			} else if (month == 9) {
				employerCountOct++;
			} else if (month == 10) {
				employerCountNov++;
			} else if (month == 11) {
				employerCountDec++;
			}
		}
		employersJan.put("label", "January");
		employersJan.put("y", employerCountJan);
		
		employersFeb.put("label", "February");
		employersFeb.put("y", employerCountFeb);
		
		employersMar.put("label", "March");
		employersMar.put("y", employerCountMar);
		
		employersApr.put("label", "April");
		employersApr.put("y", employerCountApr);
		
		employersMay.put("label", "May");
		employersMay.put("y", employerCountMay);
		
		employersJun.put("label", "June");
		employersJun.put("y", employerCountJun);
		
		employersJul.put("label", "July");
		employersJul.put("y", employerCountJul);
		
		employersAug.put("label", "August");
		employersAug.put("y", employerCountAug);
		
		employersSep.put("label", "September");
		employersSep.put("y", employerCountSep);
		
		employersOct.put("label", "October");
		employersOct.put("y", employerCountOct);
		
		employersNov.put("label", "November");
		employersNov.put("y", employerCountNov);
		
		employersDec.put("label", "December");
		employersDec.put("y", employerCountDec);
		
		jsonArray.put(employersJan);
		jsonArray.put(employersFeb);
		jsonArray.put(employersMar);
		jsonArray.put(employersApr);
		jsonArray.put(employersMay);
		jsonArray.put(employersJun);
		jsonArray.put(employersJul);
		jsonArray.put(employersAug);
		jsonArray.put(employersSep);
		jsonArray.put(employersOct);
		jsonArray.put(employersNov);
		jsonArray.put(employersDec);
		
		return jsonArray;
	}
	
}
