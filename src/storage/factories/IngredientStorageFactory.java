package storage.factories;

import storage.interfaces.IngredientStorage;
import storage.impl.IngredientStorageImpl;
import storage.impl.IngredientStorageImplTest;

public class IngredientStorageFactory {

	public static IngredientStorage getStorage(){
		return new IngredientStorageImpl();
	}

}
