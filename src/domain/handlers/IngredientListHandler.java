package domain.handlers;

import java.util.ArrayList;

import domain.models.Ingredient;
import domain.models.Recipe;
import log.Log;

public class IngredientListHandler {


	ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	Log log = new Log();

	private int id;


	public IngredientListHandler(){

	}

	public void addIngredient(Ingredient ingredient){
			log.write(String.format("%s added to recipe.",ingredient.toString()));
			ingredientList.add(ingredient);
			}

	public void clearIngredients(){
		ingredientList.clear();
	}

	public ArrayList<Ingredient> getIngredientList(){
		return ingredientList;
	}
	public Ingredient getLatestAdded(){
		return ingredientList.get(ingredientList.size()-1);
	}
	public void deleteLatest(){
		ingredientList.remove(ingredientList.get(ingredientList.size()-1));
	}
	public boolean checkList(Ingredient ingredient){
		for(Ingredient i : ingredientList){
			if(i.toString().equals(ingredient.toString())){
				return false;
			}
		}
		return true;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}

}
