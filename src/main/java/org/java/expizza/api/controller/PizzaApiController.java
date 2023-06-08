package org.java.expizza.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.expizza.pojo.Pizza;
import org.java.expizza.pojo.SpecialOffer;
import org.java.expizza.serv.PizzaService;
import org.java.expizza.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PizzaApiController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;

	@GetMapping("/pizzas")
	public ResponseEntity<List<Pizza>> apiIndex() {

		List<Pizza> pizzas = pizzaService.findAll();
		
		if (pizzas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pizzas, HttpStatus.OK);
		}
	}
	
	@GetMapping("pizzas/filter")
	public ResponseEntity<List<Pizza>> filterByName(@RequestParam(required = false) String name) {
		
		List<Pizza> pizzas = pizzaService.findByName(name);
		
		if (pizzas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pizzas, HttpStatus.OK);
		}
	}

	@GetMapping("/pizzas/{id}")
	public ResponseEntity<Pizza> apiShow(@PathVariable("id") int id) {

		Optional<Pizza> pizzaOpt = pizzaService.findById(id);

		if (pizzaOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			Pizza pizza = pizzaOpt.get();
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		}
	}
	
	@PostMapping("/pizzas/store")
	public ResponseEntity<Pizza> apiCreate(@RequestBody Pizza pizza) {
		
		Pizza newPizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(newPizza, HttpStatus.OK);
	}
	
	@PutMapping("/pizzas/update")
	public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizza) {
		
		Pizza updatedPizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(updatedPizza, HttpStatus.OK);	
	}
	
	@DeleteMapping("/pizzas/delete/{id}")
	public ResponseEntity<Pizza> deletePizza(@PathVariable int id) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		
		Pizza pizza = optPizza.get();
		
		for (SpecialOffer so : pizza.getSpecialOffers()) {
			specialOfferService.delete(so);
		}
		pizzaService.delete(pizza);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
