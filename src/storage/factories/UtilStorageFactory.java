package storage.factories;

import storage.impl.UtilStorageImpl;
import storage.interfaces.UtilStorage;

public class UtilStorageFactory {

	public static UtilStorage getStorage(){
		return new UtilStorageImpl();
	}

}
