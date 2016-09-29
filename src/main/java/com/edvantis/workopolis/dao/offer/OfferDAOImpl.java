package com.edvantis.workopolis.dao.offer;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.vacancy.VacancyDAOImpl;
import com.edvantis.workopolis.model.user.employer.Offer;


public class OfferDAOImpl implements OfferDAO {

static Logger log = Logger.getLogger(VacancyDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	@Override
	public List<Offer> getOffers() {
		log.info("start getOffers");
		Session session = sessionFactory.openSession();
		List<Offer> offers = session.createQuery("from Offer as Offers").list();
		log.info("offers " + offers);
		session.close();
		return offers;
	}

	@Override
	public Offer getOfferById(int id) {
		Session session = sessionFactory.openSession();
		Offer offer = (Offer) session.get(Offer.class, id);
		log.debug("offer " + offer);
		session.close();
		return offer;
	}

	@Override
	public void createOffer(Offer offer) {
		Session session = this.sessionFactory.openSession();
		session.save(offer);
		session.close();
		log.info("Offer added successfully");
		
	}

	@Override
	public void deleteOffer(int id) {
		Session session = this.sessionFactory.openSession();
		Offer offer = (Offer)session.load(Offer.class, new Integer(id));
		if (null  != offer){
			session.delete(offer);
		
		}
		session.flush();
		session.close();
		
	}

	@Override
	public List<Offer> getEmployerOffers(int employerId) {
		Session session = this.sessionFactory.openSession();
		List<Offer> offers = session.createQuery("from Offer as Offers where EmployerId =" + employerId).list();
		session.close();
		return offers;
	}

	@Override
	public void updateOffer(Offer offer) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(offer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Offer> getCandidateOffers(int candidateId) {
		Session session = sessionFactory.openSession();
		List<Offer> offers  =session.createQuery("from Offer as Offers where candidateId=" + candidateId).list();
		session.close();
		return offers;
	}

}
