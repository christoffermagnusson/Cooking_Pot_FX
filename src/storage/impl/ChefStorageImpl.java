package storage.impl;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.models.Chef;
import storage.interfaces.ChefStorage;
import storage.util.DBConnection;
import storage.util.DBConverter;
import storage.util.StorageException;
import log.Log;

public class ChefStorageImpl extends Observable implements ChefStorage {

	private ArrayList<Observer> observerList = new ArrayList<Observer>();

	@Override
	public void storeChef(Chef chef) {
		if(chef.getId()==0){
		try{
			String insert = String.format("INSERT INTO chef VALUES('%s','%s')",chef.getFirstName(),chef.getLastName());
			DBConnection.getInstance().update(insert);
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
	}
		if(chef.getId()!=0){
			try{
				String update = String.format("UPDATE chef SET firstname = '%s', lastname = '%s' WHERE id = %s"
						,chef.getFirstName(),chef.getLastName(),chef.getId());
				DBConnection.getInstance().update(update);
			}catch(StorageException se){
				Log.write(se.getMessage());
			}

		}
	}

	@Override
	public Chef fetchChef(String lastName) {
		Chef chef = null;
		try{
			String query = String.format("SELECT * FROM chef WHERE lastname = '%s'",lastName);
			chef = DBConverter.getInstance().toChef(DBConnection.getInstance().execQuery(query));

		}catch(StorageException se){
			Log.write(se.getMessage());
		}finally{
			return chef;
		}
	}

	@Override
	public ArrayList<Chef> fetchChefList() {
		ArrayList<Chef> array = null;
		try{
			String query = String.format("SELECT * FROM chef");
			array = DBConverter.getInstance().toChefList(DBConnection.getInstance().execQuery(query));
		}catch(StorageException se){
			Log.write(se.getMessage());
		}finally{
			return array;
		}

	}

	@Override
	public void addObserver(Observer obs){
		observerList.add(obs);
	}
	@Override
	public void deleteObservers(){
		observerList.clear();
	}
	public void notifyObservers(Object obj){
		for(Observer obs : observerList){
			obs.update(this,obj);
		}
	}

	@Override
	public Chef fetchUserChef(String username) {
		Chef chef = null;
		try{
			String idString = String.format("SELECT chefid FROM cooking_pot.user WHERE username='%s'",username);
			int id = DBConverter.getInstance().getId(DBConnection.getInstance().execQuery(idString));
			String fetchString = String.format("SELECT * FROM cooking_pot.chef WHERE id=%d",id);

			chef = DBConverter.getInstance().toChef(DBConnection.getInstance().execQuery(fetchString));
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return chef;
	}

	@Override
	public int getLatestID() {
		int latest = 0;
		try{
			String fetchString = "SELECT currval('chef_id_seq')";
			latest = DBConverter.getInstance().getId(DBConnection.getInstance().execQuery(fetchString));
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return latest;
	}


	}


