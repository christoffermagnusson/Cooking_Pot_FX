package storage.impl;

import java.util.ArrayList;

import domain.models.Chef;
import storage.interfaces.ChefStorage;
import log.Log;

public class ChefStorageImplTest implements ChefStorage {

	ArrayList<Chef> chefList = new ArrayList<Chef>();
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

}
