package storage.impl;

import java.util.ArrayList;

import log.Log;
import storage.interfaces.UtilStorage;
import storage.util.*;

public class UtilStorageImpl implements UtilStorage {

	@Override
	public ArrayList<String> getTimeUnitTypes() {
		ArrayList<String> typeArray = new ArrayList<String>();
		try{
			String fetchString = "SELECT * FROM timeunit";
			typeArray = DBConverter.getInstance().toTimeUnitTypeList(DBConnection.getInstance().execQuery(fetchString));
		}catch(StorageException se){
			Log.write(se.getMessage());
		}
		return typeArray;

	}

}
