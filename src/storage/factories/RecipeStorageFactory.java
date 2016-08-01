package storage.factories;

import storage.impl.RecipeStorageImplTest;
import storage.impl.RecipeStorageImpl;
import storage.interfaces.RecipeStorage;

public class RecipeStorageFactory {

	public static RecipeStorage getStorage(){
		return new RecipeStorageImpl();
	}
}
