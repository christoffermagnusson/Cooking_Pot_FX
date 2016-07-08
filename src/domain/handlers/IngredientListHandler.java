package domain.handlers;

import java.util.ArrayList;

import domain.models.Ingredient;
import domain.models.Recipe;
import log.Log;

public class IngredientListHandler {


	ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	Log log = new Log();

	public IngredientListHandler(){

	}

	public void addIngredient(Ingredient ingredient){
			log.write(String.format("%s added to recipe.",ingredient.toString()));
			ingredientList.add(ingredient);
			}

	public ArrayList<Ingredient> getIngredientList(){
		return ingredientList;
	}
	public boolean checkList(Ingredient ingredient){
		for(Ingredient i : ingredientList){
			if(i.toString().equals(ingredient.toString())){
				return false;
			}
		}
		return true;
	}

}
