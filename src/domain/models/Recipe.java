package domain.models;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Recipe {

	private final StringProperty recipeName;
	private final ObjectProperty<Chef> recipeChef;
	private final ObjectProperty<IngredientType> recipePrimaryIngredientType;

	public Recipe(String name, Chef chef, IngredientType primaryIngredientType){
		this.recipeName = new SimpleStringProperty(name);
		this.recipeChef = new SimpleObjectProperty<Chef>(chef);
		this.recipePrimaryIngredientType = new SimpleObjectProperty<IngredientType>(primaryIngredientType);
	}

	public StringProperty recipeNameProperty() {
		return this.recipeName;
	}


	public String getRecipeName() {
		return this.recipeNameProperty().get();
	}


	public void setRecipeName(String recipeName) {
		this.recipeNameProperty().set(recipeName);
	}


	public ObjectProperty<Chef> recipeChefProperty() {
		return this.recipeChef;
	}


	public Chef getRecipeChef() {
		return this.recipeChefProperty().get();
	}


	public void setRecipeChef(Chef recipeChef) {
		this.recipeChefProperty().set(recipeChef);
	}


	public ObjectProperty<IngredientType> recipePrimaryIngredientTypeProperty() {
		return this.recipePrimaryIngredientType;
	}


	public IngredientType getRecipePrimaryIngredientType() {
		return this.recipePrimaryIngredientTypeProperty().get();
	}


	public void setRecipePrimaryIngredientType(IngredientType recipePrimaryIngredientType) {
		this.recipePrimaryIngredientTypeProperty().set(recipePrimaryIngredientType);
	}

	public String toString(){
		return this.recipeName.get();
	}


}
