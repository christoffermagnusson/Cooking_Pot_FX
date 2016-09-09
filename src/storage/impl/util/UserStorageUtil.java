package storage.impl.util;

import log.Log;
import storage.util.*;
import java.util.HashMap;

public class UserStorageUtil {


	public HashMap<Boolean,String> validate(String valueUsername,String valuePassword){
		HashMap<Boolean,String> validatedMap = new HashMap<Boolean,String>();
		try{
			String checkUsername = String.format("SELECT username FROM cooking_pot.user WHERE username='%s'"
												,valueUsername);
			String checkPassword = String.format("SELECT password FROM cooking_pot.user WHERE password='%s'"
												,valuePassword);
			if(checkIfExist(checkUsername)==false && checkIfExist(checkPassword)==false){
				validatedMap.put(false,"username"); validatedMap.put(false,"password");
				return validatedMap;
			}
			else if(checkIfExist(checkUsername)==false && checkIfExist(checkPassword)==true){
				validatedMap.put(false,"username"); validatedMap.put(true,"password");
				return validatedMap;
			}
			else if(checkIfExist(checkUsername)==true && checkIfExist(checkPassword)==false){
				validatedMap.put(true,"username"); validatedMap.put(false,"password");
				return validatedMap;
			}
			}
		catch(NullPointerException npe){
			npe.printStackTrace();
		}
		return validatedMap;

	}

	public boolean checkIfExist(String checkString) {
		try{
			String checked = DBConverter.getInstance().toSingleStringAttribute(DBConnection.getInstance().execQuery(checkString));
			if(checked==null){
				return false;
			}
		}
		catch(StorageException se){
			Log.write(se.getMessage());
		}
		return true;
	}

}
