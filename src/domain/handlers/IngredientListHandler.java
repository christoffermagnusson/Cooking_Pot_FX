package domain.handlers;

import java.util.ArrayList;

import domain.models.Ingredient;
import domain.models.Recipe;
import log.Log;

public class IngredientListHandler {

	private Recipe recipe;
	ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	Log log = new Log();

	public IngredientListHandler(Recipe recipe){
		this.recipe=recipe;
	}

	public void addIngredient(Ingredient ingredient){
		ingredientList.add(ingredient);
		// here should be some storage statement.. perhaps class is redundant
		log.write(String.format("%s added to %s",ingredient.toString(),this.recipe.toString()));
	}
	public ArrayList<Ingredient> getIngredientList(){
		return ingredientList;
	}
	public Recipe getRecipe(){
		return recipe;
	}
}
