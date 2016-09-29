package com.edvantis.workopolis.model.user.candidate;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.edvantis.workopolis.model.Experiance;
import com.edvantis.workopolis.model.education.Education;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.location.Address;
import com.edvantis.workopolis.model.skill.CommunicationSkill;
import com.edvantis.workopolis.model.skill.PersonalSkill;
import com.edvantis.workopolis.model.skill.TechnicalSkill;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;

@Entity
@Table(name="Candidates")
@PrimaryKeyJoinColumn(name="UserId")
public class Candidate extends User {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "DateOfBirth")
	private Date dateofBirth;
	
	@Column(name = "Telnumber")
	private int telNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "CandidateId", updatable = true, insertable = true)
	private Set<Education> educations  = new LinkedHashSet<Education>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "CandidateId", updatable = true, insertable = true)
	private Set<Experiance> experiances = new LinkedHashSet<Experiance>();
	
	
	//One to many bidirectional
	@OneToMany(mappedBy = "candidate")
	private Set<Interview> scheduledInterviews = new LinkedHashSet<Interview>();
	
	
	//One to many unidirectional
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CandidateId", updatable = true, insertable = true)
	private Set <TechnicalSkill> candidateTechnicalSkills = new LinkedHashSet<TechnicalSkill>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CandidateId", updatable = true, insertable = true)
	private Set <CommunicationSkill> candidateCommunicationSkills = new LinkedHashSet<CommunicationSkill>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CandidateId", updatable = true, insertable = true)
	private Set <PersonalSkill> candidatePersonalSkills = new LinkedHashSet<PersonalSkill>();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Interested_Direction")
	private Direction interstedDirection;
	
	@Column(name="CandidatePhotoUrl")
	private String photoUrl;
	
	@Column(name = "CV_Url")
	private String CVUrl;
	
	@Column(name = "CandidateState")
	private CandidateState state;
	
	@Column(name="Skype")
	private String skypeUrl;
	
	@Column(name = "Facebook")
	private String facebookUrl;
	
	@Column(name = "LinkedIn")
	private String linkedInUrl;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private Address address = new Address();
	
	@Column(name = "Gender")
	private Gender gender;

	@Column(name = "AdditionalInformation")
	private String additionalInformation;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "appliedCandidates")
	private Set<Vacancy> appliedVacancys;
	
	
	public Set<Vacancy> getAppliedVacancys() {
		return appliedVacancys;
	}

	public void setAppliedVacancys(Set<Vacancy> appliedVacancys) {
		this.appliedVacancys = appliedVacancys;
	}


	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public Set<Experiance> getExperiances() {
		return experiances;
	}

	public void setExperiances(Set<Experiance> experiances) {
		this.experiances = experiances;
	}
	
	public Set<Interview> getScheduledInterviews() {
		return scheduledInterviews;
	}
	
	public void setScheduledInterviews(Set<Interview> scheduledInterviews) {
		this.scheduledInterviews = scheduledInterviews;
	}

	public Set<TechnicalSkill> getCandidateTechnicalSkills() {
		return candidateTechnicalSkills;
	}

	public void setCandidateTechnicalSkills(
			Set<TechnicalSkill> candidateTechnicalSkills) {
		this.candidateTechnicalSkills = candidateTechnicalSkills;
	}
	
	public void setTechnicalSkill(TechnicalSkill candidateTechnicalSkill) {
		this.candidateTechnicalSkills.add(candidateTechnicalSkill);
	}

	public void setCommunicationSkill(CommunicationSkill communicationSkill) {
		this.candidateCommunicationSkills.add(communicationSkill);
	}
	
	public void setPersonalSkill(PersonalSkill personalSkill) {
		this.candidatePersonalSkills.add(personalSkill);
	}
	
	public Set<CommunicationSkill> getCandidateCommunicationSkills() {
		return candidateCommunicationSkills;
	}

	public void setCandidateCommunicationSkills(
			Set<CommunicationSkill> candidateCommunicationSkills) {
		this.candidateCommunicationSkills = candidateCommunicationSkills;
	}

	public Set<PersonalSkill> getCandidatePersonalSkills() {
		return candidatePersonalSkills;
	}

	public void setCandidatePersonalSkills(
			Set<PersonalSkill> candidatePersonalSkills) {
		this.candidatePersonalSkills = candidatePersonalSkills;
	}

	
	public Direction getInterstedDirection() {
		return interstedDirection;
	}

	public void setInterstedDirection(Direction interstedDirection) {
		this.interstedDirection = interstedDirection;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getCVUrl() {
		return CVUrl;
	}

	public void setCVUrl(String cVUrl) {
		CVUrl = cVUrl;
	}
	@Enumerated(EnumType.STRING)
	public CandidateState getState() {
		return state;
	}

	public void setState(CandidateState state) {
		this.state = state;
	}

	
	

	public String getSkypeUrl() {
		return skypeUrl;
	}

	public void setSkypeUrl(String skypeUrl) {
		this.skypeUrl = skypeUrl;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}


	public int getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(int telNumber) {
		this.telNumber = telNumber;
	}

	public Set<Education> getEducations() {
		return educations;
	}

	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}

	public Candidate() {
		super();
	}

	public Candidate(int id, String firstName, String middleName,
			String lastName, String password, String email, boolean IsActive, Date registrationDate) {
		super(id, firstName, middleName, lastName, password, email, IsActive, registrationDate);
	}

	
	public Candidate(Date dateofBirth, int telNumber,
			Set<Education> educations, Set<Experiance> experiances,
			Set<Interview> scheduledInterviews,
			Set<TechnicalSkill> candidateTechnicalSkills,
			Set<CommunicationSkill> candidateCommunicationSkills,
			Set<PersonalSkill> candidatePersonalSkills,
			Direction interstedDirection, String photoUrl, String cVUrl,
			CandidateState state, String skypeUrl, String facebookUrl,
			String linkedInUrl, Address address, Gender gender,
			String additionalInformation, Set<Vacancy> appliedVacancys) {
		super();
		this.dateofBirth = dateofBirth;
		this.telNumber = telNumber;
		this.educations = educations;
		this.experiances = experiances;
		this.scheduledInterviews = scheduledInterviews;
		this.candidateTechnicalSkills = candidateTechnicalSkills;
		this.candidateCommunicationSkills = candidateCommunicationSkills;
		this.candidatePersonalSkills = candidatePersonalSkills;
		this.interstedDirection = interstedDirection;
		this.photoUrl = photoUrl;
		CVUrl = cVUrl;
		this.state = state;
		this.skypeUrl = skypeUrl;
		this.facebookUrl = facebookUrl;
		this.linkedInUrl = linkedInUrl;
		this.address = address;
		this.gender = gender;
		this.additionalInformation = additionalInformation;
		this.appliedVacancys = appliedVacancys;
	}

//	@Override
//	public String toString() {
//		return "Candidate [dateofBirth=" + dateofBirth + ", telNumber="
//				+ telNumber + ", educations=" + educations + ", experiances="
//				+ experiances + ", scheduledInterviews=" + scheduledInterviews
//				+ ", candidateTechnicalSkills=" + candidateTechnicalSkills
//				+ ", candidateCommunicationSkills="
//				+ candidateCommunicationSkills + ", candidatePersonalSkills="
//				+ candidatePersonalSkills + ", interstedDirection="
//				+ interstedDirection + ", photoUrl=" + photoUrl + ", CVUrl="
//				+ CVUrl + ", state=" + state + ", skypeUrl=" + skypeUrl
//				+ ", facebookUrl=" + facebookUrl + ", linkedInUrl="
//				+ linkedInUrl + ", address=" + address + ", gender=" + gender
//				+ ", additionalInformation=" + additionalInformation
//				+ ", appliedVacancys=" + appliedVacancys + "]";
//	}

	

}

