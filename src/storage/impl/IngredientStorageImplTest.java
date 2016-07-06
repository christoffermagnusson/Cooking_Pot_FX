package storage.impl;

import java.util.ArrayList;

import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import domain.handlers.IngredientListHandler;
import storage.interfaces.IngredientStorage;
import log.Log;

public class IngredientStorageImplTest implements IngredientStorage {

	private ArrayList<IngredientType> typeArray = new ArrayList<IngredientType>();
	private ArrayList<Ingredient> ingredientArray = new ArrayList<Ingredient>();
	private Log log = new Log();
	private IngredientListHandler handler = null;
	private ArrayList<IngredientListHandler> handlerList = new ArrayList<IngredientListHandler>();

	public IngredientStorageImplTest(){
		initArrays();
	}

	private void initArrays(){
		typeArray.add(new IngredientType("Minced meat","gr"));
		typeArray.add(new IngredientType("Tuna","gr"));
		typeArray.add(new IngredientType("Milk","litre"));
		typeArray.add(new IngredientType("Water","litre"));
	}

	@Override
	public void storeIngredientType(IngredientType type) {
		typeArray.add(type);
		log.write(String.format("%s stored.",type));

	}

	@Override
	public IngredientType fetchIngredientType(String typeName) {
		for(IngredientType it : typeArray){
		if(it.toString().equals(typeName)){
		log.write(String.format("%s fetched.",it));
		return it;
		}
		}
		return null;
	}

	@Override
	public void storeIngredients(Recipe recipe, ArrayList<Ingredient> ingredients) {
		handler = new IngredientListHandler(recipe);
		for(Ingredient i : ingredients){
			handler.addIngredient(i);
		}
		handlerList.add(handler);
	}

	@Override
	public IngredientListHandler fetchIngredients(Recipe recipe) {
		for(IngredientListHandler ilh : handlerList){
			if(ilh.getRecipe().toString().equals(recipe.toString())){
				return ilh;
			}
		}
		return null;
	}

	@Override
	public ArrayList<IngredientType> fetchAllIngredientTypes() {
		
		for(IngrdientType it : typeArray){
			log.write(String.format("%s fetched.",it.toString()));
		}
		return typeArray;
	}

}
