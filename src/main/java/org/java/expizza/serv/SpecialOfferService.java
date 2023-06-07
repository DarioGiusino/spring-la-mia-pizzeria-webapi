package org.java.expizza.serv;

import java.util.List;
import java.util.Optional;

import org.java.expizza.pojo.SpecialOffer;
import org.java.expizza.repo.SpecialOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
	
	@Autowired
	private SpecialOfferRepo specialOfferRepo;
	
	public List<SpecialOffer> findAll() {
		
		return specialOfferRepo.findAll();
	}
	
	public SpecialOffer save(SpecialOffer specialOffer) {
		
		return specialOfferRepo.save(specialOffer);
	}
	
	public Optional<SpecialOffer> findById(int id) {
		
		return specialOfferRepo.findById(id);
	}
	
	public void delete(SpecialOffer specialOffer) {
		
		specialOfferRepo.delete(specialOffer);
	}
}
