package org.java.expizza;

import java.time.LocalDate;

import org.java.expizza.auth.pojo.Role;
import org.java.expizza.auth.pojo.User;
import org.java.expizza.auth.serv.RoleService;
import org.java.expizza.auth.serv.UserService;
import org.java.expizza.pojo.Ingredient;
import org.java.expizza.pojo.Pizza;
import org.java.expizza.pojo.SpecialOffer;
import org.java.expizza.serv.IngredientServ;
import org.java.expizza.serv.PizzaService;
import org.java.expizza.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private IngredientServ ingredientServ; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingredient i1 = new Ingredient("Mozzarella");
		Ingredient i2 = new Ingredient("Pomodoro");
		Ingredient i3 = new Ingredient("Prezzemolo");
		Ingredient i4 = new Ingredient("Sale");
		Ingredient i5 = new Ingredient("Pepe");
		Ingredient i6 = new Ingredient("Mortadella");
		
		ingredientServ.save(i1);
		ingredientServ.save(i2);
		ingredientServ.save(i3);
		ingredientServ.save(i4);
		ingredientServ.save(i5);
		ingredientServ.save(i6);
		
//		--------------------------------------------------------------------------------------------------------------------
		
		Pizza pizza1 = new Pizza("Margherita", "La bella Margherita", "https://picsum.photos/200/300", 9.99f, i1, i3);
		Pizza pizza2 = new Pizza("Diavola", "La bella Diavola", "https://picsum.photos/200/300", 10.99f, i2, i4);
		Pizza pizza3 = new Pizza("Napoli", "La bella Napoli", "https://picsum.photos/200/300", 11.99f, i5, i6);
		Pizza pizza4 = new Pizza("Parmigiana", "La bella Parmigiana", "https://picsum.photos/200/300", 12.99f);
		Pizza pizza5 = new Pizza("Biancaneve", "La bella Biancaneve", "https://picsum.photos/200/300", 13.99f, i4);
		
		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		
//		--------------------------------------------------------------------------------------------------------------------
		
		SpecialOffer so1 = new SpecialOffer("Special Offer 1", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-10-01"), 10, pizza1);
		SpecialOffer so2 = new SpecialOffer("Special Offer 2", LocalDate.parse("2023-02-01"), LocalDate.parse("2023-09-01"), 20, pizza2);
		SpecialOffer so3 = new SpecialOffer("Special Offer 3", LocalDate.parse("2023-03-01"), LocalDate.parse("2023-08-01"), 30, pizza3);
		SpecialOffer so4 = new SpecialOffer("Special Offer 4", LocalDate.parse("2023-04-01"), LocalDate.parse("2023-07-01"), 40, pizza4);
		SpecialOffer so5 = new SpecialOffer("Special Offer 5", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-06-01"), 50, pizza5);
		SpecialOffer so6 = new SpecialOffer("Special Offer 6", LocalDate.parse("2023-06-01"), LocalDate.parse("2023-11-01"), 60, pizza1);
		SpecialOffer so7 = new SpecialOffer("Special Offer 7", LocalDate.parse("2023-07-01"), LocalDate.parse("2023-12-01"), 5, pizza3);
		SpecialOffer so8 = new SpecialOffer("Special Offer 8", LocalDate.parse("2023-08-01"), LocalDate.parse("2024-01-01"), 80, pizza5);
		
		specialOfferService.save(so1);
		specialOfferService.save(so2);
		specialOfferService.save(so3);
		specialOfferService.save(so4);
		specialOfferService.save(so5);
		specialOfferService.save(so6);
		specialOfferService.save(so7);
		specialOfferService.save(so8);
		
//		--------------------------------------------------------------------------------------------------------------------
		
		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");
		
		roleService.save(userRole);
		roleService.save(adminRole);
		
//		--------------------------------------------------------------------------------------------------------------------
		
		final String psw = new BCryptPasswordEncoder().encode("password");
		
		User userUser = new User("user", psw, userRole);
		User userAdmin = new User("admin", psw, adminRole);
				
		userService.save(userUser);
		userService.save(userAdmin);
	}
}
