package storage.impl;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.handlers.IngredientListHandler;
import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import storage.interfaces.IngredientStorage;
import storage.util.DBConnection;
import storage.util.DBConverter;
import storage.util.StorageException;
import log.Log;

public class IngredientStorageImpl extends Observable implements IngredientStorage {

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
	public void storeIngredientType(IngredientType type) {
		try{
				String checkQuery = String.format("SELECT * FROM ingredienttype WHERE name = '%s'",type.getName());
				IngredientType checkType = DBConverter.getInstance().toIngredientType(DBConnection.getInstance().execQuery(checkQuery));
				if(checkType==null){
					String insert = String.format("INSERT INTO ingredienttype VALUES('%s','%s')",type.getName(),type.measurement());
					DBConnection.getInstance().update(insert);
					Log.write(String.format("Insert succesful : %s",insert));
				}
				else{
					String update = String.format("UPDATE ingredienttype SET measurement = '%s' WHERE name = '%s'",type.measurement(),type.getName());
					DBConnection.getInstance().update(update);
					Log.write(String.format("Update succesful : %s",update));
				}
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
	}

	@Override
	public IngredientType fetchIngredientType(String typeName) {
		IngredientType type = null;
		try{
				String query = String.format("SELECT * FROM ingredienttype WHERE name = '%s'",typeName);
				type = DBConverter.getInstance().toIngredientType(DBConnection.getInstance().execQuery(query));
				Log.write(String.format("Successfully fetched : %s",type.getName()));
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		catch(NullPointerException npe){
			Log.write(String.format("IngredientType of name %s does not exist in storage",typeName));
		}
		finally{
			return type;
		}
	}

	@Override
	public ArrayList<IngredientType> fetchAllIngredientTypes() {
		ArrayList<IngredientType> array = null;
		try{
			String query = String.format("SELECT * FROM cooking_pot.ingredienttype");
			array = DBConverter.getInstance().toTypeList(DBConnection.getInstance().execQuery(query));
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		catch(NullPointerException npe){
			Log.write(String.format("No ingredienttypes in db : %s",npe.getMessage()));
		}
		finally{
		return array;
	}
	}

	/**
	*		Stores ingredients associated with specified recipe. 
	*		@args 	recipe 				Recipe whoms ingredients shall be stored.
	*		@args 	ingredients 	List of ingredients taken from input from the user when creating a new recipe. 
	*													possibly from GUI or controllers.
	*/
	@Override
	public void storeIngredients(Recipe recipe, ArrayList<Ingredient> ingredients) {
		try{
			if(recipe.getRecipeIngredientListHandler().getId()==0){
				String insertList = String.format("INSERT INTO ingredientlist (recipename) VALUES('%s')",recipe.getRecipeName());
				DBConnection.getInstance().update(insertList);
				int handlerId = DBConverter.getInstance().getId(DBConnection.getInstance().execQuery("SELECT currval('ingredientlist_id_seq')"));
				recipe.getRecipeIngredientListHandler().setId(handlerId);
				for(Ingredient i : ingredients){
					String insertIngredient = String.format("INSERT INTO handler VALUES('%s','%d','%d')"
						,i.getType().getName()
						,i.getAmount()
						,handlerId);
					DBConnection.getInstance().update(insertIngredient);
				}
			}
		}
		catch(NullPointerException npe){
			npe.printStackTrace();
			Log.write(npe.getMessage());
		}
		catch(StorageException se){
			Log.write("test" +se.getMessage());
		}
	}

	/**
	*		Initiates and returns ingredients associated with specified recipe
	*		@args recipe 	Recipe whoms ingredients to be fetched. Cannot be done with fresh recipe before 
	*									it has been stored to storage.
	*		@return handler 	Handler to be returned. Contains all ingredients associated with specified recipe.
	**/
	@Override
	public IngredientListHandler fetchIngredients(Recipe recipe) {
		IngredientListHandler handler = recipe.getRecipeIngredientListHandler();
		ArrayList<Ingredient> ingredientList = null;
		try{
			String fetchString = String.format("SELECT * FROM handler WHERE listid = %d"
				,handler.getId());

			ingredientList = DBConverter.getInstance().toIngredientList(DBConnection.getInstance().execQuery(fetchString));
			for(Ingredient i : ingredientList){
				handler.addIngredient(i);
			}
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return handler;
		
	}

}
