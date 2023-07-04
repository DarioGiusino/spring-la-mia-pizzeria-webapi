package org.java.expizza.repo;

import java.util.List;

import org.java.expizza.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
	
	public List<Pizza> findByNameContaining(String name);
}