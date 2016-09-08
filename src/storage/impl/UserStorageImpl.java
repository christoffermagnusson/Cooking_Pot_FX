package storage.impl;

import domain.models.User;
import storage.util.*;
import storage.interfaces.UserStorage;
import log.Log;
import storage.impl.util.UserStorageUtil;

public class UserStorageImpl implements UserStorage {

	@Override
	public void storeUser(User user) {
		try{
			String insertString = String.format("INSERT INTO user VALUES('%s','%s',%d)"
					,user.getUsername()
					,user.getPassword()
					,user.getChef().getId());
			DBConnection.getInstance().execQuery(insertString);
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}

	}

	@Override
	public User getUser(String username,String password) {
		User user = null;
		try{

			String fetchString = String.format("SELECT * FROM cooking_pot.\"user\" WHERE username ='%s' AND password='%s'"
					,username
					,password);
			user = DBConverter.getInstance().toUser(DBConnection.getInstance().execQuery(fetchString));


		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return user;
	}

}
