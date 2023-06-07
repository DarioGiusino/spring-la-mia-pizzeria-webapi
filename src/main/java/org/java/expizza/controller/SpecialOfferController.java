package org.java.expizza.controller;

import java.util.Optional;

import org.java.expizza.pojo.Pizza;
import org.java.expizza.pojo.SpecialOffer;
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

import jakarta.validation.Valid;

@Controller
public class SpecialOfferController {
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/admin/pizza/{pizzaId}/special-offer/create")
	public String create(Model model, @PathVariable("pizzaId") int pizzaId ) {
		
		model.addAttribute("specialOffer", new SpecialOffer());
		model.addAttribute("pizzaId", pizzaId);
		
		return "/special-offer/create";
	}
	
	@PostMapping("/admin/pizza/{pizzaId}/special-offer/store")
	public String store(@Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult, Model model, @PathVariable("pizzaId") int pizzaId){
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("specialOffer", specialOffer);
			model.addAttribute("errors", bindingResult);
			
			return "/special-offer/create";
		}
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(pizzaId);
		Pizza pizza = pizzaOpt.get();
		specialOffer.setPizza(pizza);
		
		specialOfferService.save(specialOffer);
		
		return "redirect:/pizza/" + pizzaId;
	}
	
	@GetMapping("/admin/pizza/{pizzaId}/special-offer/edit/{id}")
	public String edit(@PathVariable("id") int id, @PathVariable("pizzaId") int pizzaId, Model model) {
		
		Optional<SpecialOffer> specialOfferOpt = specialOfferService.findById(id);
		SpecialOffer specialOffer = specialOfferOpt.get();
		
		model.addAttribute("specialOffer", specialOffer);
		model.addAttribute("pizzaId", pizzaId);
		
		return "/special-offer/edit";
	}
	
	@PostMapping("/admin/pizza/{pizzaId}/special-offer/update/{id}")
	public String update(@PathVariable("pizzaId") int pizzaId, @Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("specialOffer", specialOffer);
			model.addAttribute("errors", bindingResult);
			
			return "/special-offer/edit";
		}
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(pizzaId);
		Pizza pizza = pizzaOpt.get();
		specialOffer.setPizza(pizza);
		
		specialOfferService.save(specialOffer);
		
		return "redirect:/pizza/" + pizzaId;
	}
}
