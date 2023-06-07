package org.java.expizza.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Please, insert a name.")
	@Size(min = 3, max = 25, message = "The name must be 3-25 char.")
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String picture;

	@NotNull(message = "Please, insert a price.")
	@Min(value = 1, message = "Min price is 1.")
	private float price;

	@OneToMany(mappedBy = "pizza")
	private List<SpecialOffer> specialOffers;

	@ManyToMany
	private List<Ingredient> ingredients;

	public Pizza() {}

	public Pizza(String name, String description, String picture, float price) {
		setName(name);
		setDescription(description);
		setPicture(picture);
		setPrice(price);
	}

	public Pizza(String name, String description, String picture, float price, Ingredient... ingredients) {
		this(name, description, picture, price);
		setIngredients(ingredients);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public void setIngredients(Ingredient[] ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getName() + "\nDescription: " + getDescription() + "\nPicture: " + getPicture()
				+ "\nPrice: €" + getPrice();
	}

	public void removeIngredient(Ingredient ingredient) {
		getIngredients().remove(ingredient);		
	}
}
