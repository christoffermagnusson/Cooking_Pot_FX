package storage.factories;

import storage.interfaces.ChefStorage;
import storage.impl.ChefStorageImplTest;
import storage.impl.ChefStorageImpl;

public class ChefStorageFactory {

	public static ChefStorage getStorage(){
		return new ChefStorageImpl();
	}

}
