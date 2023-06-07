package org.java.expizza.controller;

import java.util.List;
import java.util.Optional;

import org.java.expizza.pojo.Ingredient;
import org.java.expizza.pojo.Pizza;
import org.java.expizza.pojo.SpecialOffer;
import org.java.expizza.serv.IngredientServ;
import org.java.expizza.serv.PizzaService;
import org.java.expizza.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientServ ingredientServ;
	
	@Autowired
	private SpecialOfferService specialOfferServ;
	
	@GetMapping("/")
	public String index(Model model){
		List<Pizza> pizzaList = pizzaService.findAll();
		
		model.addAttribute("pizzaList", pizzaList);

		return "pizza/index";
	}
	
	@GetMapping("/pizza/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findByIdWithSpecialOffer(id);
		
		Pizza pizza = pizzaOpt.get();
		List<SpecialOffer> specialOffers = pizza.getSpecialOffers();
		List<Ingredient> ingredients = pizza.getIngredients();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza/show";
	}
	
	@PostMapping("/pizza/filter")
	public String filterPizza(Model model, @RequestParam(required = false) String name) {
		
		List<Pizza> pizzaList = pizzaService.findByName(name);
		model.addAttribute("pizzaList", pizzaList);
		model.addAttribute("name", name);
		
		return "pizza/index";
	}
	
	@GetMapping("/admin/pizza/create")
	public String create(Model model) {
		List<Ingredient> ingredients = ingredientServ.findAll();
		
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredients);
		
		return "pizza/create";
	}
	
	@PostMapping("/admin/pizza/store")
	public String store(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model){
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "pizza/create";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/admin/pizza/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		List<Ingredient> ingredients = ingredientServ.findAll();
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza/edit";
	}
	
	@PostMapping("/admin/pizza/update/{id}")
	public String update(@PathVariable("id") int id, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "pizza/edit";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/admin/pizza/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		for (SpecialOffer so : pizza.getSpecialOffers()) {
			specialOfferServ.delete(so);
		}
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
}
