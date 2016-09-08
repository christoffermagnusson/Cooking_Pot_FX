package storage.impl.util;

import domain.models.User;
import log.Log;
import storage.util.*;
import java.util.HashMap;

public class UserStorageUtil {


	public HashMap<Boolean,String> validate(String valueUsername,String valuePassword){
		HashMap<Boolean,String> validatedMap = new HashMap<Boolean,String>();
		User user = null;
		try{
			String checkString = String.format("SELECT * FROM cooking_pot.\"user\" WHERE username='%s' AND password='%s'"
												,valueUsername
												,valuePassword);
			user = DBConverter.getInstance().toUser(DBConnection.getInstance().execQuery(checkString));

			if(user.getUsername()==valueUsername && user.getPassword()==valueUsername){
				validatedMap.put(true,"username"); validatedMap.put(true,"password");
				return validatedMap;
			}
			else if(user.getUsername()!=valueUsername && user.getPassword()==valuePassword){
				validatedMap.put(false,"username"); validatedMap.put(true,"password");
				return validatedMap;
			}
			else if(user.getUsername()==valueUsername && user.getPassword()!=valuePassword){
				validatedMap.put(true,"username"); validatedMap.put(false,"password");
				return validatedMap;
			}
			}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return validatedMap;

	}

}
