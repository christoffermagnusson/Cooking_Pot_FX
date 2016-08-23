package storage.impl;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import domain.handlers.IngredientListHandler;
import storage.interfaces.IngredientStorage;
import log.Log;

public class IngredientStorageImplTest extends Observable implements IngredientStorage {

	private ArrayList<IngredientType> typeArray = new ArrayList<IngredientType>();
	private ArrayList<Ingredient> ingredientArray = new ArrayList<Ingredient>();
	private Log log = new Log();
	private IngredientListHandler handler = null;
	private ArrayList<IngredientListHandler> handlerList = new ArrayList<IngredientListHandler>();
	private ArrayList<Observer> observerList = new ArrayList<Observer>();

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
		if(!typeArray.contains(type)){
		typeArray.add(type);
		log.write(String.format("%s stored.",type));
		notifyObservers(typeArray);
		}


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
		handler = recipe.getRecipeIngredientListHandler();
		for(Ingredient i : ingredients){
			handler.addIngredient(i);
		}
		handlerList.add(handler);
	}

	@Override
	public IngredientListHandler fetchIngredients(Recipe recipe) {

		// fetch an handler from list..
		return null;
	}

	@Override
	public ArrayList<IngredientType> fetchAllIngredientTypes() {

		for(IngredientType it : typeArray){
			log.write(String.format("%s fetched.",it.toString()));
		}
		return typeArray;
	}

	@Override
	public void addObserver(Observer obs) {
		observerList.add(obs);

	}

	@Override
	public void notifyObservers(Object obj) {
		for(Observer obs : observerList){
			obs.update(this,obj);
		}
	}
	@Override
	public void deleteObservers(){
		observerList.clear();
	}

	@Override
	public void removeIngredient(Ingredient ingredient, int listId) {
		// TODO Auto-generated method stub
		
	}

}
