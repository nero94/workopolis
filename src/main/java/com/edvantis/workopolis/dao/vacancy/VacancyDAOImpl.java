package com.edvantis.workopolis.dao.vacancy;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edvantis.workopolis.dao.company.CompanyDAO;
import com.edvantis.workopolis.dao.location.AddressDAO;
import com.edvantis.workopolis.dao.location.CountryDAO;
import com.edvantis.workopolis.model.Company;
import com.edvantis.workopolis.model.skill.PersonalSkill;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.model.vacancy.VacancyState;

@Repository("vacancyDAO")
public class VacancyDAOImpl implements VacancyDAO {

	static Logger log = Logger.getLogger(VacancyDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CountryDAO countryDAO;

	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private CompanyDAO companyDAO;

	public List<Vacancy> getAllVacancies() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Vacancy.class);
		List<Vacancy> result = criteria.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vacancy> getVacancies(String keyword, Integer salary,
			Direction direction, String country, Integer offset,
			Integer maxResults) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Vacancy.class);
		if (keyword != null)
			criteria.add(Restrictions.like("title", "%" + keyword + "%"));
		if (salary != null)
			criteria.add(Restrictions.ge("salary", salary));
		if (direction != null)
			criteria.add(Restrictions.eq("direction", direction));

		if (country != null && country != "") {
			criteria.createAlias("company", "com")
					.createAlias("com.address", "adr")
					.createAlias("adr.country", "c")
					.add(Restrictions.eq("c.name", country));
		}
		criteria.add(Restrictions.in("state", new VacancyState[] {VacancyState.POSTED, VacancyState.PROCESSING}));
		criteria.setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10).list();
		criteria.addOrder(Order.desc("postDate"));
		List<Vacancy> result = criteria.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Vacancy> getNotApprovedVacancies(){
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Vacancy.class);
		criteria.add(Restrictions.eq("state", VacancyState.NOT_APPROVED));
		List<Vacancy> result = criteria.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	@Override
	public List<Vacancy> getClosedVacancies(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Vacancy.class);
		criteria.add(Restrictions.eq("state", VacancyState.CLOSED));
		List<Vacancy> result = criteria.list();
		session.getTransaction().commit();
		session.close();
		return result;
	};
	
	@Override
	public Long count(String keyword, Integer salary, Direction direction,
			String country) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vacancy.class);
		if (keyword != null)
			criteria.add(Restrictions.like("title", "%" + keyword + "%"));
		if (salary != null)
			criteria.add(Restrictions.ge("salary", salary));
		if (direction != null)
			criteria.add(Restrictions.eq("direction", direction));
		if (country != null && country != "") {
			criteria.createAlias("company", "com")
					.createAlias("com.address", "adr")
					.createAlias("adr.country", "c")
					.add(Restrictions.eq("c.name", country));

		}
		criteria.add(Restrictions.in("state", new VacancyState[] {VacancyState.POSTED, VacancyState.PROCESSING}));
		Long res = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return res;
	}

	@Override
	public Long count(String keyword, Direction direction) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vacancy.class);
		if (keyword != null)
			criteria.add(Restrictions.like("title", "%" + keyword + "%"));
		if (direction != null)
			criteria.add(Restrictions.eq("direction", direction));
		criteria.add(Restrictions.like("state", VacancyState.POSTED));
		Long res = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		log.debug("res--> " + res);
		return res;
	}

	@Override
	public Vacancy getVacancyById(int id) {
		Session session = sessionFactory.openSession();
		Vacancy vacancy = (Vacancy) session.get(Vacancy.class, id);
		Hibernate.initialize(vacancy.getCommunicationSkills());
		Hibernate.initialize(vacancy.getPersonalSkills());
		Hibernate.initialize(vacancy.getTechnicalSkills());
		for (Candidate candidate : vacancy.getAppliedCandidates()) {
			Hibernate.initialize(candidate.getCandidateTechnicalSkills());
			Hibernate.initialize(candidate.getCandidateCommunicationSkills());
			Hibernate.initialize(candidate.getCandidatePersonalSkills());
		}
		log.debug("vacancy " + vacancy);
		session.close();
		return vacancy;
	}

	@Override
	public void addVacancy(Vacancy vacancy) {
		Session session = sessionFactory.openSession();
		System.err.println(vacancy);
		session.save(vacancy);
		session.close();
		log.info("Vacancy added successfully");
	}

	@Override
	public void addSkill(PersonalSkill skill) {
		Session session = this.sessionFactory.openSession();
		session.save(skill);
		session.close();
		log.info("Skill added successfully");
	}

	@Override
	public void updateVacancy(Vacancy vacancy) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(vacancy);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deteteVacancy(int id) {
		Session session = this.sessionFactory.openSession();
		Vacancy vacancy = (Vacancy) session.load(Vacancy.class, id);
		if (null != vacancy) {
			session.delete(vacancy);
			session.flush();
			log.info("interview deleted");
		}
		session.close();
	}

	@Override
	public List<Vacancy> getNotAssignVacancies(String keyword,
			Direction direction, String companyName, String country,
			Integer offset, Integer maxResults) {
		Session session = sessionFactory.openSession();
		log.info("start getNotAssignVacancies");
		List<Vacancy> vacancies = new ArrayList<Vacancy>();
		Criteria criteria = session.createCriteria(Vacancy.class);
		criteria.add(Restrictions.like("state", VacancyState.POSTED));
		if (keyword != null && keyword != "")
			criteria.add(Restrictions.like("title", "%" + keyword + "%"));
		if (direction != null)
			criteria.add(Restrictions.eq("direction", direction));

		if (companyName != null && companyName != "") {
			Company company = companyDAO.getCompanyByName(companyName);
			criteria.add(Restrictions.eq("company", company));
		}

		if (country != null && country != "") {
			criteria.createAlias("company", "com")
			.createAlias("com.address", "adr")
			.createAlias("adr.country", "c")
			.add(Restrictions.eq("c.name", country));
		}

//		for (Vacancy vacancy : vacancies) {
//			System.err.println(vacancy.getId());
//			// System.err.println(vacancy.getTechnicalSkills());
//			// Hibernate.initialize(vacancy.getTechnicalSkills());
//		}

		criteria.setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10).list();
		vacancies = criteria.list();

		session.close();
		return vacancies;
	}
	
	@Override
	public Long count(String keyword, Direction direction, String company, String country, Integer offset, Integer maxResults) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vacancy.class);
		if (keyword != null && keyword != "")
			criteria.add(Restrictions.like("title", "%" + keyword + "%"));
		if (direction != null)
			criteria.add(Restrictions.eq("direction", direction));
		if (company != null && company!="")
			criteria.createAlias("company", "com").add(Restrictions.eq("com.name", company));
		if (country != null && country != "") {
			criteria.createAlias("company", "com")
					.createAlias("com.address", "adr")
					.createAlias("adr.country", "c")
					.add(Restrictions.eq("c.name", country));

		}
		criteria.add(Restrictions.eq("state", VacancyState.POSTED));
		Long res = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return res;
	}

	@Override
	public List<Vacancy> getRecruiterVacancies(Integer offset,
			Integer maxResults, Recruiter recruiter) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Vacancy.class);
		criteria.add(Restrictions.eq("RecruiterId", recruiter.getId()));
		criteria.setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10).list();
		session.close();
		return criteria.list();

	}
	

}
