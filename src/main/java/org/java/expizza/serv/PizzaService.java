package org.java.expizza.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.expizza.pojo.Pizza;
import org.java.expizza.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepo pizzaRepo;
	
	public List<Pizza> findAll() {
		
		return pizzaRepo.findAll();
	}
	
	public Pizza save(Pizza pizza) {
		
		return pizzaRepo.save(pizza);
	}
	
	public Optional<Pizza> findById(int id) {
		
		return pizzaRepo.findById(id);
	}
	
	@Transactional
	public Optional<Pizza> findByIdWithSpecialOffer(int id) {
		
		Optional<Pizza> pizzaOpt = pizzaRepo.findById(id);
		Hibernate.initialize(pizzaOpt.get().getSpecialOffers());
		
		return pizzaOpt;
	}
	
	public List<Pizza> findByName(String name) {
		
		return pizzaRepo.findByNameContaining(name);
	}
	
	public void delete(Pizza pizza) {
		
		pizzaRepo.delete(pizza);
	}
}
