package com.edvantis.workopolis.dao.user;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.interviewer.InterviewerDAO;
import com.edvantis.workopolis.dao.role.RoleDAO;
import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.Manager;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.user.Role;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.UserRegistration;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.user.employer.Employer;

import org.apache.commons.beanutils.BeanUtils;

public class UserDAOImpl implements UserDAO {
	
	static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private InterviewerDAO interviewerDAO;
	
	@Override
	public List<User> getUsers() {
		log.info("start getUsers");
		Session session = sessionFactory.openSession();
		List<User> users = session.createQuery("from User as Users").list();
		log.debug("users " + users);
		session.close();
		log.info("end getUsers");
		return users;
	}
	
	@Override
	public User getUser(int id) {
		log.info("start getUser by id");
		Session session = sessionFactory.openSession();
		User user = (User) session.createQuery("from User as Users where Id= '"+id+"'").uniqueResult();
		//log.debug("user " + user);
		session.close();
		log.info("end getUser by id");
		return user;
	}

	@Override
	public User getUser(String email) {
		log.info("start getUser by email");
		Session session = sessionFactory.openSession();
		User user = (User) session.createQuery("from User as Users where Email= '"+email+"'").uniqueResult();
		session.close();
		log.info("end getUser by email");
		return user;
	}

	@Override
	public void save(User user) {
		log.info("start save user");
		Session session = sessionFactory.openSession();
		session.save(user);
		session.close();
		log.info("end save user");
	}

	@Override
	public void removeUser(int id) {
		log.info("start remove user");
		Session session = sessionFactory.openSession();
    	
    	User myObject ;
    	myObject = (User)session.load(User.class,id);
    	session.delete(myObject);

    	//This makes the pending delete to be done
    	session.flush() ;
    	
    	session.close();
    	log.info("end remove user");
	}

	@Override
	public void confirm(String uuid) {
		log.info("start confirm");
		log.debug("uuid " + uuid);
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		UserRegistration accountRegistration = (UserRegistration) session.createQuery("from UserRegistration as UserRegistration where hash = '"+uuid+"'").uniqueResult();
		log.debug("accauntRegistration " + accountRegistration);
		int idAccount = accountRegistration.getIdAccount();
		User account = (User) session.createQuery("from User as Users where id = " + idAccount).uniqueResult();
		log.debug("account " + account);
		account.setIsActive(true);
		log.debug("account " + account);
		session.getTransaction().commit(); 
    	session.close();
		
	}
	
	@Override
	 public void editUser(User user){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	 }
	
	
	public void register(User account, UUID uuid) throws NonUniqueResultException {
		log.info("start register");
		log.debug("account==> " + account);
		log.debug("account.getClass().getSimpleName()==> " + account.getClass().getSimpleName());
		String roleName = null;
		
		//self-registration
		if(account.getClass().getSimpleName().equals("Candidate"))
			roleName = "ROLE_CANDIDATE";
		
		if(account.getClass().getSimpleName().equals("Employer"))
			roleName = "ROLE_EMPLOYER";
		
		log.debug("roleName==> " + roleName);
		account.setRole(roleDAO.getRoleByName(roleName));
		Session session = null;
		int accFromDB = 0; 
		
		session = sessionFactory.openSession(); 
		session.beginTransaction();
			
		session.save(account);
		User accauntFromDB = (User) session.createQuery("from User as Users where Email= '"+account.getEmail()+"'").uniqueResult();
		accFromDB = accauntFromDB.getId();
				
		//insert uuid into table
		UserRegistration accountRegistration = new UserRegistration();
		accountRegistration.setIdAccount(accFromDB);
		accountRegistration.setHash(uuid.toString());
		log.debug("accountRegistration==> " + accountRegistration);
		session.save(accountRegistration);
		session.getTransaction().commit();
		session.close();
		log.info("end register");
	}
	
	public void registerByAdmin(Employer account, UUID uuid) throws NonUniqueResultException {
		log.info("start register");
		log.debug("account==> " + account);
		log.debug("account.getClass().getSimpleName()==> " + account.getClass().getSimpleName());
		Session session = null;
		session = sessionFactory.openSession(); 
		session.beginTransaction();
		log.debug("account.getRole().getName() " + account.getRole().getName());
		if(account.getRole().getName().toString().equals("ROLE_INTERVIEWER")) {
			Interviewer interviewer = new Interviewer();
			try {
				BeanUtils.copyProperties(interviewer, account);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			}
			log.debug("interviewer--> " + interviewer);
			session.save(interviewer);
		}
		if(account.getRole().getName().toString().equals("ROLE_CANDIDATE")) {
			Candidate candidate = new Candidate();
			try {
				BeanUtils.copyProperties(candidate, account);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			}
			log.debug("interviewer--> " + candidate);
			session.save(candidate);
		}
		if(account.getRole().getName().toString().equals("ROLE_RECRUITER")) {
			Recruiter recruiter = new Recruiter();
			try {
				BeanUtils.copyProperties(recruiter, account);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			}
			log.debug("recruiter--> " + recruiter);
			session.save(recruiter);
		}
		if(account.getRole().getName().toString().equals("ROLE_EMPLOYER")) {
			Employer employer = new Employer();
			try {
				BeanUtils.copyProperties(employer, account);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			}
			log.debug("employer--> " + employer);
			session.save(employer);
		}
		if(account.getRole().getName().toString().equals("ROLE_MANAGER")) {
			Manager manager = new Manager();
			try {
				BeanUtils.copyProperties(manager, account);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			}
			log.debug("manager--> " + manager);
			session.save(manager);
		}
		session.getTransaction().commit();
		session.close();
		log.info("end register");
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
	public void update(User user) {
		log.info("start update");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		log.info("end update");
	}

	@Override
	public void changePassword(String uuid, String newPassword) {
		log.info("start changePassword");
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		UserRegistration accountRegistration = (UserRegistration) session.createQuery("from UserRegistration as UserRegistration where hash = '"+uuid+"'").uniqueResult();
		log.debug("accauntRegistration " + accountRegistration);
		int idAccount = accountRegistration.getIdAccount();
		User account = (User) session.createQuery("from User as Users where id = " + idAccount).uniqueResult();
		log.debug("account " + account);
		account.setPassword(newPassword);
		session.update(account);
		session.getTransaction().commit();
		session.close();
	}
	
	

	
}
