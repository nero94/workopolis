package com.edvantis.workopolis.dao.candidate;

import java.util.List;

import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Direction;

public interface CandidateDAO {
	
	 public List<Candidate> getCandidates(Direction direction, String location, Integer ageFrom, Integer ageTo, Integer offset, Integer maxResults);
	 
	 public List<Candidate> getCandidates(String felterQuery);
	 
	 public Candidate getCandidateById(int id);
	 
	 public String getCvNameByEmail(String email);
	 
	 public void addCandidate(Candidate candidate);
	 
	 public void updateVacancy(Candidate vacancy);
	 
	 public void deteteCandidate(int id);
	 
	 public void deteteCandidateCV(String email);
	 
	 public void addCandidateCV(String filename, String email);
	 
	 public String getPhotoNameByEmail(String email);
	 
	 public Candidate getCandidateByEmail(String email);
	 
	 public void applyVacancy(int candidateId, int vacancyId);
	 
	 public void updateCandidate(Candidate candidate);

	public Long count(Direction direction, String location, Integer ageFrom, Integer ageTo);
	
	public List<Candidate> getAllCandidates();
	
	public void addPhoto(String filename, String email);
	
}
