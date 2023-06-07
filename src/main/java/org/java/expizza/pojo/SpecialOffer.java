package org.java.expizza.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	@Min(0)
	@NotNull
	private int discount;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;

	public SpecialOffer() {	}

	public SpecialOffer(String title, LocalDate startDate, LocalDate endDate, int discount, Pizza pizza) {
		setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setDiscount(discount);
		setPizza(pizza);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getDiscount() {
		return discount;
	}
	
	public String getDiscountedPrice() {
		float pizzaPrice = getPizza().getPrice();
		
		float discountedPrice = pizzaPrice - (pizzaPrice * getDiscount() / 100);
		
		return String.format("%,.2f", discountedPrice);
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] " + getTitle()
			+"\nData di inizio: " + getStartDate()
			+"\nData di fine: " + getEndDate()
			+"\nSconto applicato: " + getDiscount() + "%";
	}
}
