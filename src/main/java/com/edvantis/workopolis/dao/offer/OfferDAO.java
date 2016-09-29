package com.edvantis.workopolis.dao.offer;

import java.util.List;


import com.edvantis.workopolis.model.user.employer.Offer;

public interface OfferDAO {

	public List<Offer> getOffers();
	
	public Offer getOfferById(int id);
	
	public void createOffer(Offer offer);
	
	public void deleteOffer(int id);
	
	public List<Offer> getEmployerOffers(int employerId);
	
	public void updateOffer(Offer offer);
	
	public List<Offer> getCandidateOffers(int candidateId);
}
