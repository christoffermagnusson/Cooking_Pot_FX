package domain.models;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import domain.handlers.IngredientListHandler;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Recipe {

	private final StringProperty recipeName;
	private final ObjectProperty<Chef> recipeChef;
	private final ObjectProperty<IngredientType> recipePrimaryIngredientType;
	private final ObjectProperty<IngredientListHandler> recipeIngredientListHandler;
	private final StringProperty description;

	private int id;

	public Recipe(String name, Chef chef, IngredientType primaryIngredientType,IngredientListHandler list,String description){
		this.recipeName = new SimpleStringProperty(name);
		this.recipeChef = new SimpleObjectProperty<Chef>(chef);
		this.recipePrimaryIngredientType = new SimpleObjectProperty<IngredientType>(primaryIngredientType);
		this.recipeIngredientListHandler = new SimpleObjectProperty<IngredientListHandler>(list);
		this.description = new SimpleStringProperty(description);
	}
	public StringProperty recipeDescriptionProperty(){
		return description;
	}
	public String getDescription(){
		return description.get();
	}
	public void setDescription(String description){
		this.description.set(description);
	}
	public ObjectProperty<IngredientListHandler> recipeIngredientListHandlerProperty(){
		return this.recipeIngredientListHandler;
	}
	public IngredientListHandler getRecipeIngredientListHandler(){
		return this.recipeIngredientListHandler.get();
	}
	public void setRecipeIngredientListHandler(IngredientListHandler handler){
		this.recipeIngredientListHandler.set(handler);
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
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}

	public String toString(){
		return this.recipeName.get();
	}


}
