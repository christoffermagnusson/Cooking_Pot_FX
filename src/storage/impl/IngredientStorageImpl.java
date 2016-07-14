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

	@Override
	public void storeIngredients(Recipe recipe, ArrayList<Ingredient> ingredients) {

	}

	@Override
	public IngredientListHandler fetchIngredients(Recipe recipe) {
		// TODO Auto-generated method stub
		return null;
	}

}
