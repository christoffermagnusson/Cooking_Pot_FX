package storage.factories;

import storage.impl.RecipeStorageImplTest;
import storage.interfaces.RecipeStorage;

public class RecipeStorageFactory {

	public static RecipeStorage getStorage(){
		return new RecipeStorageImplTest();
	}
}
