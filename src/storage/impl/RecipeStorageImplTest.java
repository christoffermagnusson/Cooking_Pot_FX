package storage.impl;

import java.util.ArrayList;

import domain.models.Chef;
import domain.models.IngredientType;
import domain.models.Recipe;
import storage.interfaces.RecipeStorage;
import log.Log;
import storage.factories.ChefStorageFactory;
import storage.interfaces.ChefStorage;
import storage.factories.IngredientStorageFactory;
import storage.interfaces.IngredientStorage;

public class RecipeStorageImplTest implements RecipeStorage {

	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	private Log log = new Log();
	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;

	public RecipeStorageImplTest(){


	}

		public void setStorages(ChefStorage chefStorage,IngredientStorage ingredientStorage){
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		initArray();
	}

	 private void initArray(){
		recipeList.add(new Recipe("Meatball Marinara",chefStorage.fetchChef("Magnusson")
			,ingredientStorage.fetchIngredientType("Minced meat")));
		recipeList.add(new Recipe("Tuna sandwich",chefStorage.fetchChef("Blackby")
			,ingredientStorage.fetchIngredientType("Tuna")));
	}

	@Override
	public void storeRecipe(Recipe recipe) {
		recipeList.add(recipe);
		log.write(String.format("%s stored.",recipe));
	}

	@Override
	public Recipe fetchRecipe(String name) {
		for(Recipe r : recipeList){
			if(r.toString().equals(name)){
				log.write(String.format("%s fetched.",r));
				return r;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Recipe> fetchRecipe(Chef chef) {
		ArrayList<Recipe> tmpArray = new ArrayList<Recipe>();
		for(Recipe r : recipeList){
			if(r.getRecipeChef().toString().equals(chef.toString())){
				tmpArray.add(r);
				log.write(String.format("%s fetched.",r));
			}
		}
		return tmpArray;
	}

	@Override
	public ArrayList<Recipe> fetchRecipe(IngredientType type) {
		ArrayList<Recipe> tmpArray = new ArrayList<Recipe>();
		for(Recipe r : recipeList){
			if(r.getRecipePrimaryIngredientType().toString().equals(type.toString())){
				tmpArray.add(r);
				log.write(String.format("%s fetched.",r));
			}
		}
		return tmpArray;
	}

}
