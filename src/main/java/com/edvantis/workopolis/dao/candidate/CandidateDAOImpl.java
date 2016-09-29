package com.edvantis.workopolis.dao.candidate;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.location.AddressDAO;
import com.edvantis.workopolis.dao.location.CountryDAO;
import com.edvantis.workopolis.dao.role.RoleDAO;
import com.edvantis.workopolis.dao.user.UserDAO;

import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Direction;

public class CandidateDAOImpl implements CandidateDAO {
	
	static Logger log = Logger.getLogger(CandidateDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidate> getCandidates(Direction direction, String country, Integer ageFrom, Integer ageTo, Integer offset, Integer maxResults){
		log.info("start getCandidates");
		log.debug("location==> " + country);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Candidate.class);
		if (direction != null) {
			log.debug("direction--> " + direction);
			criteria.add(Restrictions.eq("interstedDirection", direction));
		}
		if ((ageFrom != null) || (ageTo != null)) {
			log.debug("ageFrom " + ageFrom);
			log.debug("ageTo " + ageTo);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			log.debug("currentYear " + currentYear);
			if (ageFrom != null){
				Date dataTo = new Date(currentYear-ageFrom-1900, 0, 0);
				log.debug("dataTo " + dataTo);
				criteria.add(Restrictions.lt("dateofBirth", dataTo));
			}
			if (ageTo != null){
			Date dataFrom = new Date(currentYear-ageTo-1900, 0, 0);
			log.debug("dataFrom " + dataFrom);
			criteria.add(Restrictions.ge("dateofBirth", dataFrom)); 
			}
		}
		if (country != null && country != "") {
			criteria.createAlias("address", "adr")
			.createAlias("adr.country", "c")
			.add(Restrictions.eq("c.name", country));
		}
		
		criteria.setFirstResult(offset != null ? offset : 0)
		.setMaxResults(maxResults != null ? maxResults : 10).list();
		log.debug("offset " + offset + "maxResults " + maxResults);
		List<Candidate> candidates = criteria.list();
		for(int i =0; i<candidates.size(); i++){
			Hibernate.initialize(candidates.get(i).getCandidateTechnicalSkills());
			Hibernate.initialize(candidates.get(i).getCandidateCommunicationSkills());
			Hibernate.initialize(candidates.get(i).getAdditionalInformation());
		}
		
		session.getTransaction().commit();
		session.close();
		System.out.println("candidates---> " + candidates);
		log.info("end getCandidates");
		return candidates;
	}
	
	@Override
	public Long count(Direction direction, String country, Integer ageFrom, Integer ageTo) {
		log.info("count start");
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Candidate.class);
		if (direction != null) 
			criteria.add(Restrictions.eq("interstedDirection", direction));
		
		if ((ageFrom != null) || (ageTo != null)) {
			log.debug("ageFrom " + ageFrom);
			log.debug("ageTo " + ageTo);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			log.debug("currentYear " + currentYear);
			if (ageFrom != null){
				Date dataTo = new Date(currentYear-ageFrom-1900, 0, 0);
				log.debug("dataTo " + dataTo);
				criteria.add(Restrictions.lt("dateofBirth", dataTo));
			}
			if (ageTo != null){
				Date dataFrom = new Date(currentYear-ageTo-1900, 0, 0);
				log.debug("dataFrom " + dataFrom);
				criteria.add(Restrictions.ge("dateofBirth", dataFrom)); 
			}
		}
		
		if (country != null && country != "") {
			criteria.createAlias("address", "adr")
			.createAlias("adr.country", "c")
			.add(Restrictions.eq("c.name", country));
		}
		
		Long res = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();		
		log.info("count end");
		return res;
	}
	
	@Override
	public List<Candidate> getCandidates(String filterQuery){
		Session session = sessionFactory.openSession();
		List<Candidate> candidates = session.createQuery("from Candidate as Candidates " + filterQuery).list();
		session.close();
		return candidates;
	}
	
	@Override
	public void addCandidate(Candidate candidate) {
		Session session = this.sessionFactory.openSession();
		session.save(candidate);
		session.close();
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(candidate);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deteteCandidate(int id) {
		Session session = this.sessionFactory.openSession();
		Candidate candidate = (Candidate)session.load(Candidate.class, new Integer(id));
		if (candidate != null) session.delete(candidate);
		session.close();
	}

	@Override
	public Candidate getCandidateById(int id) {
		log.info("start getCandidateById");
		Session session = sessionFactory.openSession();
		Candidate candidate = (Candidate) session.get(Candidate.class, id);
		Hibernate.initialize(candidate.getCandidateTechnicalSkills());
		Hibernate.initialize(candidate.getCandidatePersonalSkills());
		Hibernate.initialize(candidate.getCandidateCommunicationSkills());
		Hibernate.initialize(candidate.getEducations());
		Hibernate.initialize(candidate.getExperiances());
		log.debug("candidate " + candidate);
		session.close();
		log.info("end getCandidateById");
		return candidate;
	}

	@Override
	public void updateVacancy(Candidate vacancy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCvNameByEmail(String email) {
		Session session = sessionFactory.openSession();
		User user = userDAO.getUser(email);
		int userId = user.getId();
		String cvName = (String)session.createSQLQuery("select CV_Url from Candidates where Candidates.UserId = '"+userId+"'").uniqueResult();
		session.close();
		return cvName;
	}

	@Override
	public void deteteCandidateCV(String email) {
		log.info("start deteteCandidateCV");
		log.debug("email " + email);
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		tx = session.beginTransaction();
		String cvNameByEmail = getCvNameByEmail(email);
		log.debug("cvNameByEmail " + cvNameByEmail);
		Candidate candidate = (Candidate) session.createQuery("from Candidate as Candidates where CV_Url= '"+cvNameByEmail+"'").uniqueResult();
		log.debug("candidate " + candidate);
		candidate.setCVUrl("");
		session.update(candidate); 
		tx.commit();
		session.close();
		log.info("end deteteCandidateCV");
	}

	@Override
	public void addCandidateCV(String filename, String email) {
		log.info("start addCandidateCV");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		String cvNameByEmail = getCvNameByEmail(email);
		log.debug("cvNameByEmail " + cvNameByEmail);
		Candidate candidate = (Candidate) session.createQuery("from Candidate as Candidates where Email= '"+email+"'").uniqueResult();
		log.debug("candidate " + candidate);
		candidate.setCVUrl(filename);
		session.update(candidate); 
		tx.commit();
		session.close();
		log.info("end deteteCandidateCV");
	}

	@Override
	public String getPhotoNameByEmail(String email) {
		log.info("start getPhotoNameByEmail");
		log.debug("email " + email);
		Session session = sessionFactory.openSession();
		User user = userDAO.getUser(email);
		log.info("user " + user);
		int userId = user.getId();
		log.info("userId "+userId);
		String photoName = (String)session.createSQLQuery("select CandidatePhotoUrl from Candidates where Candidates.UserId = '"+userId+"'").uniqueResult();
		log.debug("cvName " + photoName);
		session.close();
		log.info("end getPhotoNameByEmail");
		return photoName;
	}

	@Override
	public Candidate getCandidateByEmail(String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Candidate candidate = null;
		try {
			candidate = (Candidate) session.createQuery("from Candidate as Candidates where Email= '"+email+"'").uniqueResult();
			Hibernate.initialize(candidate.getCandidateTechnicalSkills());
			Hibernate.initialize(candidate.getCandidatePersonalSkills());
			Hibernate.initialize(candidate.getCandidateCommunicationSkills());
			Hibernate.initialize(candidate.getEducations());
			Hibernate.initialize(candidate.getExperiances());
			Hibernate.initialize(candidate.getAppliedVacancys());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return candidate;
	}

	@Override
	public void applyVacancy(int candidateId, int vacancyId) {
		log.info("start applyVacancy");
		log.debug("candidateId " + candidateId + " vacancyId " + vacancyId);
		Session session = sessionFactory.openSession();
		SQLQuery insertQuery = session.createSQLQuery("INSERT INTO applied_candidates(candidateId, vacancyId)VALUES("+candidateId+","+vacancyId+")");
		insertQuery.executeUpdate();
		session.beginTransaction().commit();
		log.info("end applyVacancy");
	}

	@Override
	public List<Candidate> getAllCandidates() {
		Session session = sessionFactory.openSession();
		List <Candidate> candidates = session.createQuery("from Candidate as Candidates").list();
		for (int i = 0; i < candidates.size(); i++) {
			Hibernate.initialize(candidates.get(i).getCandidateTechnicalSkills());
			Hibernate.initialize(candidates.get(i).getCandidateCommunicationSkills());
			Hibernate.initialize(candidates.get(i).getCandidatePersonalSkills());
		}
		session.close();
		
		return candidates;
	}

	@Override
	public void addPhoto(String filename, String email) {
		log.info("start addPhoto");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		String imgNameByEmail = getPhotoNameByEmail(email);
		log.debug("imgNameByEmail " + imgNameByEmail);
		Candidate candidate = getCandidateByEmail(email);
		candidate.setPhotoUrl(filename);
		session.update(candidate); 
		tx.commit();
		session.close();
		log.info("end addPhoto");
	}

}
