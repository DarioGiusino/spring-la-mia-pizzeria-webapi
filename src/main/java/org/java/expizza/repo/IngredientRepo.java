package org.java.expizza.repo;

import org.java.expizza.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer> { }