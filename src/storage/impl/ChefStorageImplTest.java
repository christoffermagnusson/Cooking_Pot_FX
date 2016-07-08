package storage.impl;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.models.Chef;
import storage.interfaces.ChefStorage;
import log.Log;

public class ChefStorageImplTest extends Observable implements ChefStorage {

	ArrayList<Chef> chefList = new ArrayList<Chef>();
	ArrayList<Observer> observerList = new ArrayList<Observer>();
	Log log = new Log();

	public ChefStorageImplTest(){
		initChefList();
	}

	private void initChefList(){
		chefList.add(new Chef("Christoffer","Magnusson"));
		chefList.add(new Chef("Viktoria","Blackby"));
		chefList.add(new Chef("Shankho","Roodro"));
		chefList.add(new Chef("Gordon","Ramsay"));
		chefList.add(new Chef("Leif","Mannerström"));

	}

	@Override
	public void storeChef(Chef chef) {
		chefList.add(chef);

		log.write(String.format("%s stored.",chef));

	}

	@Override
	public Chef fetchChef(String lastName) {
		for(Chef chef : chefList){
			if(lastName.equals(chef.getLastName())){
				log.write(String.format("%s fetched.",chef));
				return chef;
			}
		}
		return null;

	}

	@Override
	public ArrayList<Chef> fetchChefList() {

		return chefList;
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
}
