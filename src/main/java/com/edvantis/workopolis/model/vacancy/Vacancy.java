package com.edvantis.workopolis.model.vacancy;


import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edvantis.workopolis.model.Company;
import com.edvantis.workopolis.model.education.EducationDegree;
import com.edvantis.workopolis.model.education.EducationField;
import com.edvantis.workopolis.model.location.Address;
import com.edvantis.workopolis.model.skill.CommunicationSkill;
import com.edvantis.workopolis.model.skill.PersonalSkill;
import com.edvantis.workopolis.model.skill.TechnicalSkill;
import com.edvantis.workopolis.model.user.candidate.Candidate;


@Entity
@Table(name="Vacancies")
public class Vacancy {
	@Id
	@GeneratedValue
	@Column(name="VacancyId")
	private int id;
	
	@Column(name="Title")
	private String title;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Direction")
	private Direction direction;
	
	@Column(name="Salary")
	private Integer salary;
	
	@Column(name="Description")
	private String description;
	
	@Column(name = "Responsibilies")
	private String responsibilities;
	
	@Column(name = "Experiance")
	private String requiredExperiance;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "companyId")
	private Company company;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "applied_candidates",
			joinColumns = @JoinColumn(name = "vacancyId"),
			inverseJoinColumns = @JoinColumn(name = "candidateId")
			) 
	private Set<Candidate> appliedCandidates;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VacancyId", updatable = true, insertable = true, nullable = true)
	private Set <TechnicalSkill> technicalSkills = new LinkedHashSet<TechnicalSkill>(); 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VacancyId", updatable = true, insertable = true, nullable = true)
	private Set <CommunicationSkill> communicationSkills = new LinkedHashSet<CommunicationSkill>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VacancyId", updatable = true, insertable = true, nullable = true)
	private Set <PersonalSkill> personalSkills = new LinkedHashSet<PersonalSkill>();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EducationField")
	private EducationField educationField;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EducationDegree")
	private EducationDegree educationDegree;
	
	@Column(name = "EmployerOffer")
	private String employerOffer;
	
	@Column(name = "Feedback")
	private String feedback;
	
	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "State")
	private VacancyState state;
		
	@Column(name="PostDate")
	private Date postDate;

	@Column(name = "CloseDate")
	private Date closeDate;
	
	
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}

	public Vacancy() {
		super();
	}

	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}




	public VacancyState getState() {
		return state;
	}
	
	

	public String getRequiredExperiance() {
		return requiredExperiance;
	}


	public void setRequiredExperiance(String requiredExperiance) {
		this.requiredExperiance = requiredExperiance;
	}


	public EducationField getEducationField() {
		return educationField;
	}
	

	public EducationDegree getEducationDegree() {
		return educationDegree;
	}


	public void setEducationDegree(EducationDegree educationDegree) {
		this.educationDegree = educationDegree;
	}


	public void setEducationField(EducationField educationField) {
		this.educationField = educationField;
	}


	public void setState(VacancyState state) {
		this.state = state;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSalary() {
		return salary;
	}


	public void setSalary(Integer salary) {
		this.salary = salary;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getResponsibilities() {
		return responsibilities;
	}


	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}



	public Set<Candidate> getAppliedCandidates() {
		return appliedCandidates;
	}


	public void setAppliedCandidates(Set<Candidate> appliedCandidates) {
		this.appliedCandidates = appliedCandidates;
	}

	public String getEmployerOffer() {
		return employerOffer;
	}


	public void setEmployerOffer(String employerOffer) {
		this.employerOffer = employerOffer;
	}



	public Date getPostDate() {
		return postDate;
	}


	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Set<TechnicalSkill> getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(Set<TechnicalSkill> technicalSkills) {
		this.technicalSkills = technicalSkills;
	}
	
	public void setTechnicalSkill(TechnicalSkill technicalSkill) {
		this.technicalSkills.add(technicalSkill);
	}
	
	public void setCommunicationSkill(CommunicationSkill communicationSkill) {
		this.communicationSkills.add(communicationSkill);
	}
	
	public void setPersonalSkill(PersonalSkill personalSkill) {
		this.personalSkills.add(personalSkill);
	}

	public Set<CommunicationSkill> getCommunicationSkills() {
		return communicationSkills;
	}

	public void setCommunicationSkills(Set<CommunicationSkill> communicationSkills) {
		this.communicationSkills = communicationSkills;
	}

	public Set<PersonalSkill> getPersonalSkills() {
		return personalSkills;
	}

	public void setPersonalSkills(Set<PersonalSkill> personalSkills) {
		this.personalSkills = personalSkills;
	}


	public Vacancy(int id, String title, Direction direction, Integer salary,
			String description, String responsibilities,
			String requiredExperiance, Company company, Status status,
			Set<Candidate> appliedCandidates,
			Set<TechnicalSkill> technicalSkills,
			Set<CommunicationSkill> communicationSkills,
			Set<PersonalSkill> personalSkills, EducationField educationField,
			EducationDegree educationDegree, String employerOffer,
			String feedback, VacancyState state, Date postDate, Date closeDate) {
		super();
		this.id = id;
		this.title = title;
		this.direction = direction;
		this.salary = salary;
		this.description = description;
		this.responsibilities = responsibilities;
		this.requiredExperiance = requiredExperiance;
		this.company = company;
		this.status = status;
		this.appliedCandidates = appliedCandidates;
		this.technicalSkills = technicalSkills;
		this.communicationSkills = communicationSkills;
		this.personalSkills = personalSkills;
		this.educationField = educationField;
		this.educationDegree = educationDegree;
		this.employerOffer = employerOffer;
		this.feedback = feedback;
		this.state = state;
		this.postDate = postDate;
		this.closeDate = closeDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacancy other = (Vacancy) obj;
		if (id != other.id)
			return false;
		return true;
	}




//	@Override
//	public String toString() {
//		return "Vacancy [id=" + id + ", title=" + title + ", location="
//				+ location + ", direction=" + direction + ", salary=" + salary
//				+ ", description=" + description + ", responsibilities="
//				+ responsibilities + ", requiredExperiance="
//				+ requiredExperiance + ", company=" + company + ", status="
//				+ status + ", appliedCandidates=" + appliedCandidates
//				+ ", technicalSkills=" + technicalSkills
//				+ ", communicationSkills=" + communicationSkills
//				+ ", personalSkills=" + personalSkills + ", educationField="
//				+ educationField + ", educationDegree=" + educationDegree
//				+ ", employerOffer=" + employerOffer + ", state=" + state
//				+ ", postDate=" + postDate
//				+ ", closeDate=" + closeDate + "]";
//	}


	



}
