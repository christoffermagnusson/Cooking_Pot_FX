package storage.impl;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.models.Chef;
import domain.models.IngredientType;
import domain.models.Recipe;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import storage.util.DBConverter;
import storage.util.DBConnection;
import storage.util.StorageException;
import log.Log;

public class RecipeStorageImpl extends Observable implements RecipeStorage {

	ChefStorage chefStorage = null;
	IngredientStorage ingredientStorage = null;
	ArrayList<Observer> observerList = new ArrayList<Observer>();

	@Override
	public void addObserver(Observer obs) {
	observerList.add(obs);
	}

	@Override
	public void deleteObservers() {
	observerList.clear();
	}

	@Override
	public void notifyObservers(Object obj) {
	for(Observer obs : observerList){
		obs.update(this,obj);
	}

	}

	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;

	}

	@Override
	public void storeRecipe(Recipe recipe) {
		try{
				String checkQuery = String.format("SELECT * FROM recipe WHERE recipename = '%s'",recipe.getRecipeName());
				Recipe checkRecipe = DBConverter.getInstance().toRecipe(DBConnection.getInstance().execQuery(checkQuery));
				if(checkRecipe==null){

					String recipeInsert = String.format("INSERT INTO recipe VALUES('%s','%s','%s','%d','%d')"
						,recipe.getRecipeName()
						,recipe.getRecipePrimaryIngredientType().getName()
						,recipe.getDescription()
						,recipe.getRecipeChef().getId()
						,recipe.getRecipeIngredientListHandler().getId());
					DBConnection.getInstance().update(recipeInsert);

					Log.write(String.format("Recipe : %s : inserted",recipe.getRecipeName()));
				}
				else{
					String recipeUpdate = String.format("UPDATE recipe SET primaryingredient = '%s', description = '%s', chefid = %d, listid = %d WHERE recipename = '%s'"
						,recipe.getRecipePrimaryIngredientType().getName()
						,recipe.getDescription()
						,recipe.getRecipeChef().getId()
						,recipe.getRecipeIngredientListHandler().getId()
						,recipe.getRecipeName());
					DBConnection.getInstance().update(recipeUpdate);

					Log.write(String.format("Recipe : %s : updated",recipe.getRecipeName()));
				}
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}

	}

	@Override
	public Recipe fetchRecipe(String name) {
		Recipe recipe = null;
		try{
			String fetchString = String.format("SELECT * FROM recipe WHERE recipename = '%s'"
				,name);
			recipe = DBConverter.getInstance().toRecipe(DBConnection.getInstance().execQuery(fetchString));
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return recipe;
	}

	@Override
	public ArrayList<Recipe> fetchRecipe(Chef chef) {
		
		return null;
	}

	@Override
	public ArrayList<Recipe> fetchRecipe(IngredientType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
