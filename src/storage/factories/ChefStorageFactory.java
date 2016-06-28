package storage.factories;

import storage.interfaces.ChefStorage;
import storage.impl.ChefStorageImplTest;

public class ChefStorageFactory {

	public static ChefStorage getStorage(){
		return new ChefStorageImplTest();
	}

}
