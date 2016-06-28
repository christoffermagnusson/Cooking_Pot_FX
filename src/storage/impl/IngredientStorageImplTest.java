package storage.impl;

import java.util.ArrayList;

import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import storage.interfaces.IngredientStorage;

public class IngredientStorageImplTest implements IngredientStorage {

	private ArrayList<IngredientType> typeArray = new ArrayList<IngredientType>();
	private ArrayList<Ingredient> ingredientArray = new ArrayList<Ingredient>();

	

	@Override
	public void storeIngredientType(IngredientType type) {
		

	}

	@Override
	public IngredientType fetchIngredientType(String typeName) {
		// TODO Auto-generated method stub
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

}
