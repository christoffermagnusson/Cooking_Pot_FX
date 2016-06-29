package storage.impl;

import java.util.ArrayList;

import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import storage.interfaces.IngredientStorage;
import log.Log;

public class IngredientStorageImplTest implements IngredientStorage {

	private ArrayList<IngredientType> typeArray = new ArrayList<IngredientType>();
	private ArrayList<Ingredient> ingredientArray = new ArrayList<Ingredient>();
	private Log log = new Log();

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
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Ingredient> fetchIngredients(Recipe recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IngredientType> fetchAllIngredientTypes() {
		
		return typeArray;
	}

}
