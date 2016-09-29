package com.edvantis.workopolis.dao.employer;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.role.RoleDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.model.Company;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.UserRegistration;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public class EmployerDAOImpl implements EmployerDAO {
	
	static Logger log = Logger.getLogger(EmployerDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public String getPhotoNameByEmail(String email) {
		log.info("start getPhotoNameByEmail");
		log.debug("email " + email);
		Session session = sessionFactory.openSession();
		User user = userDAO.getUser(email);
		//log.debug("user " + user);
		int userId = user.getId();
		log.debug("userId " + userId);
		int companyId = (Integer)session.createSQLQuery("select companyId from Employers where Employers.UserId = '"+userId+"'").uniqueResult();
		log.debug("companyId " + companyId);
		Company company = (Company) session.createQuery("from Company as Companys where CompanyId= '"+companyId+"'").uniqueResult();
		String imgUrl = company.getImgUrl();
		log.debug("imgUrl " + imgUrl);
		session.close();
		log.info("end getPhotoNameByEmail ");
		return imgUrl;
	}

	@Override
	public void editEmployer(Employer employer){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(employer);
		session.getTransaction().commit();
		session.close();
	}

	
	@Override
	public Employer getEmployerByEmail(String email) {
		log.info("start getEmployerByEmail");
		log.debug("email " + email);
		Session session = sessionFactory.openSession();
		
		Employer employer = (Employer) session.createQuery("from Employer as Empoyers where Email= '"+email+"'").uniqueResult();
		log.debug("employer " + employer);
		session.close();
		log.info("end getEmployerByEmail");
		return employer;
	}


	@Override
	public void saveUUID(String uuid, int userId) {
		log.info("start saveUUID");
		log.debug("uuid " + uuid);
		log.debug("userId " + userId);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserRegistration userRegistration = new UserRegistration();
		userRegistration.setIdAccount(userId);
		userRegistration.setHash(uuid);
		session.save(userRegistration);
		session.getTransaction().commit();
		session.close();
		log.info("end saveUUID");
		
	}

	@Override
	public void confirm(String uuid, String email) {
		log.info("start confirm");
		log.debug("uuid " + uuid);
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		UserRegistration accountRegistration = (UserRegistration) session.createQuery("from UserRegistration as UserRegistration where hash = '"+uuid+"'").uniqueResult();
		log.debug("accauntRegistration " + accountRegistration);
		int idAccount = accountRegistration.getIdAccount();
		User account = (User) session.createQuery("from User as Users where id = " + idAccount).uniqueResult();
		log.debug("account " + account);
		account.setEmail(email);
		log.debug("account " + account);
		session.update(account);
		session.getTransaction().commit(); //update email
    	session.close();
		
	}
	
	
	@Override
	public void addLogo(String filename, String email) {
		log.info("start addLogo");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		String imgNameByEmail = getPhotoNameByEmail(email);
		log.debug("imgNameByEmail " + imgNameByEmail);
		Employer employer = getEmployerByEmail(email);
		Company company = getCompanyByEmployer(employer);
		log.debug("company " + company);
		company.setImgUrl(filename);
		session.update(company); 
		tx.commit();
		session.close();
		log.info("end addLogo");
	}

	@Override
	public Company getCompanyByEmployer(Employer employer) {
		log.info("getCompanyByEmployer");
		Session session = sessionFactory.openSession();
		int companyId = employer.getCompany().getId();
		log.debug("companyId " + companyId);
		Company company = (Company)session.createQuery("from Company as Company where CompanyId = "+companyId).uniqueResult();
		log.debug("company " + company);
		session.close();
		return company;
	
		
	}
	
	@Override
	public List<Employer> getAllEmployers(){
		
		
			Session session = sessionFactory.openSession();
			List <Employer> employers = session.createQuery("from Employer as Employers").list();
			for (int i = 0; i < employers.size(); i++) {
				Hibernate.initialize(employers.get(i).getCompany());
				Hibernate.initialize(employers.get(i).getCreatedVacancies());
				Hibernate.initialize(employers.get(i).getCreatedOffers());
			}
			session.close();
			
			return employers;
	}
	
	@Override
	 public Employer getEmployerById(int id){		
		
		Session session = sessionFactory.openSession();
		Employer employer = (Employer) session.get(Employer.class, id);
		
	
		session.close();
		return employer;
	}

	
}
