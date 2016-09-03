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

	/**
	*		Inserts new or updates existing recipes. Checks if recipe exists in db: if exist = update;
	*		if not exists = insert. Remember to setID when fetching.
	**/
	@Override
	public void storeRecipe(Recipe recipe) {
		try{
				/*String checkQuery = String.format("SELECT * FROM recipe WHERE recipename = '%s'",recipe.getRecipeName());
				Recipe checkRecipe = DBConverter.getInstance().toRecipe(DBConnection.getInstance().execQuery(checkQuery));*/
				if(recipe.getId()==0){



					String recipeInsert = String.format("INSERT INTO recipe (recipename,primaryingredient,description,chefid,listid,cookingtime,timeunit) VALUES('%s','%s','%s','%d','%d','%d','%s')"
						,recipe.getRecipeName()
						,recipe.getRecipePrimaryIngredientType().getName()
						,recipe.getDescription()
						,recipe.getRecipeChef().getId()
						,recipe.getRecipeIngredientListHandler().getId()
						,recipe.getCookingTime().getCount()
						,recipe.getCookingTime().getUnit());
					DBConnection.getInstance().update(recipeInsert);

					Log.write(String.format("Recipe : %s : inserted",recipe.getRecipeName()));
				}
				else{
					String recipeUpdate = String.format("UPDATE recipe SET primaryingredient = '%s', description = '%s', chefid = %d, listid = %d, recipename = '%s',cookingTime = %d,timeunit = '%s' WHERE id = %d"
						,recipe.getRecipePrimaryIngredientType().getName()
						,recipe.getDescription()
						,recipe.getRecipeChef().getId()
						,recipe.getRecipeIngredientListHandler().getId()
						,recipe.getRecipeName()
						,recipe.getId()
						,recipe.getCookingTime().getCount()
						,recipe.getCookingTime().getUnit());
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
			int id = getId(recipe);
			recipe.setId(id);
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return recipe;
	}

	@Override
	public ArrayList<Recipe> fetchRecipe(Chef chef) {
		ArrayList<Recipe> recipeList = null;
		try{
			String fetchString = String.format("SELECT * FROM recipe WHERE chefid = %d"
				,chef.getId());
			 recipeList = DBConverter.getInstance().toRecipeList(DBConnection.getInstance().execQuery(fetchString));
			 setId(recipeList);
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return recipeList;
	}

	@Override
	public ArrayList<Recipe> fetchRecipe(IngredientType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecipe(Recipe recipe) {
		try{
			int id = recipe.getRecipeIngredientListHandler().getId();
			String deleteString = String.format("DELETE FROM ingredientlist WHERE id = %d"
					,id);
			DBConnection.getInstance().update(deleteString);
			notifyObservers(null);
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}

	}

	@Override
	public int getId(Recipe recipe) {
		int id = 0;
		try{
			String idString = String.format("SELECT id FROM recipe WHERE recipename = '%s'"
					,recipe.getRecipeName());
			id = DBConverter.getInstance().getId(DBConnection.getInstance().execQuery(idString));
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return id;
	}

	@Override
	public void setId(ArrayList<Recipe> recipes) {
		for(Recipe r : recipes){
			r.setId(getId(r));
		}

	}

}
