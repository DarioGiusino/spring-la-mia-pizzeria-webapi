package org.java.expizza.serv;

import java.util.List;
import java.util.Optional;

import org.java.expizza.pojo.Ingredient;
import org.java.expizza.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientServ {
	
	@Autowired
	private IngredientRepo ingredientRepo;
	
	public List<Ingredient> findAll() {
		
		return ingredientRepo.findAll();
	}
	
	public Ingredient save(Ingredient ingredient) {
		
		return ingredientRepo.save(ingredient);
	}
	
	public Optional<Ingredient> findById(int id) {
		
		return ingredientRepo.findById(id);
	}
	
	public void delete(Ingredient ingredient) {
		
		ingredientRepo.delete(ingredient);
	}
}