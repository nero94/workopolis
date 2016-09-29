package com.edvantis.workopolis.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




import com.edvantis.workopolis.model.skill.CommunicationSkill;
import com.edvantis.workopolis.model.skill.Importance;
import com.edvantis.workopolis.model.skill.TechnicalSkill;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public class MatchCandidatesToVacancy {

	private ArrayList<String> mathedPercentage = new ArrayList<String>();
	
	private int index = 0;
	
	public ArrayList<Candidate> findCandidates(Vacancy vacancy, List<Candidate> candidates){
		ArrayList<Candidate> mathedCandidates = new ArrayList<Candidate>();
		for (Candidate candidate : candidates) {
			if (mathedCandidates.size() == 10) {
				break;
			}
			for (TechnicalSkill candidateSkill : candidate.getCandidateTechnicalSkills()) {
				if (mathedCandidates.size() == 10) {
					break;
				}
				for (TechnicalSkill vacancySkill : vacancy.getTechnicalSkills()) {
					if(vacancySkill.getImportance() == Importance.REQUIRED){
						if(candidateSkill.getName().equals(vacancySkill.getName()) && candidateSkill.getLevel() == vacancySkill.getLevel()){
							if (mathedCandidates.contains(candidate)) {
								continue;
							} else {
							mathedCandidates.add(candidate);
							addToMatchingPercentage("90%");
							System.err.println("Adding 90%");
							
							}
							if (mathedCandidates.size() == 10) {
								break;
							}
							
						} else if(candidateSkill.getName().equals(vacancySkill.getName())){
							if (mathedCandidates.contains(candidate)) {
								continue;
							} else {
							mathedCandidates.add(candidate);
							addToMatchingPercentage("75%");
							System.err.println("Adding 75%");
							}
							if (mathedCandidates.size() == 10) {
								break;
							}
						}
					} else if(candidateSkill.getName().equals(vacancySkill.getName()) && candidateSkill.getLevel() == vacancySkill.getLevel()){
						if (mathedCandidates.contains(candidate)) {
							continue;
						} else {
						mathedCandidates.add(candidate);
						addToMatchingPercentage("65%");
						System.err.println("Adding 65%");
						}
						if (mathedCandidates.size() == 10) {
							break;
						}
					} else if(candidateSkill.getName().equals(vacancySkill.getName())){
						if (mathedCandidates.contains(candidate)) {
							continue;
						} else {
						mathedCandidates.add(candidate);
						addToMatchingPercentage("45%");
						System.err.println("Adding 45%");
						}
						if (mathedCandidates.size() == 10) {
							break;
						}
					}
				
				}
			}
			for (CommunicationSkill candidateCommunicationSkill : candidate.getCandidateCommunicationSkills()) {
				if (mathedCandidates.size() == 10) {
					break;
				}
				for (CommunicationSkill vacancySkill : vacancy.getCommunicationSkills()) {
					if(vacancySkill.getImportance() == Importance.REQUIRED){
						if(candidateCommunicationSkill.getName() == vacancySkill.getName() && candidateCommunicationSkill.getLevel() == vacancySkill.getLevel()){
							if (mathedCandidates.contains(candidate)) {
								continue;
							} else {
							mathedCandidates.add(candidate);
							addToMatchingPercentage("30%");
							System.err.println("Adding 30%");
							}
							if (mathedCandidates.size() == 10) {
								break;
							}
						} else if(candidateCommunicationSkill.getName() == vacancySkill.getName()){
							if (mathedCandidates.contains(candidate)) {
								continue;
							} else {
							mathedCandidates.add(candidate);
							addToMatchingPercentage("25%");
							System.err.println("Adding 25%");
							}
							if (mathedCandidates.size() == 10) {
								break;
							}
						}
					} else if (candidateCommunicationSkill.getName() == vacancySkill.getName() && candidateCommunicationSkill.getLevel() == vacancySkill.getLevel()) {
						if (mathedCandidates.contains(candidate)) {
							continue;
						} else {
						mathedCandidates.add(candidate);
						addToMatchingPercentage("10%");
						System.err.println("Adding 10%");
						}
						if (mathedCandidates.size() == 10) {
							break;
						}
					}
				}
			}
		}
		
		
		return mathedCandidates;
	}
	
	public void addToMatchingPercentage(String percentage) {
		
		mathedPercentage.set(index, percentage);
		setIndex(getIndex() + 1);
	}
	
	public void initialize(){
		setIndex(0);
		mathedPercentage.clear();
		for (int i = 0; i < 10; i++) {
			mathedPercentage.add("");
		}
		System.err.println("initializing mather");
	}
	
	public void sortResult(List<String> list){
		list.removeAll(Collections.singleton(null));
		list.removeAll(Collections.singleton(" "));
		list.removeAll(Collections.singleton(""));
	}
	
	public void clearMathingData(){
		mathedPercentage.clear();
	}

	public ArrayList<String> getMathedPercentage() {
		return mathedPercentage;
	}

	

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
	

	
	
	
	
}
