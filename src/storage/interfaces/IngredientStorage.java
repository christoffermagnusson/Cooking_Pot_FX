package storage.interfaces;

import domain.models.IngredientType;
import domain.handlers.IngredientListHandler;
import domain.models.Ingredient;
import domain.models.Recipe;
import java.util.ArrayList;

public interface IngredientStorage {

	void storeIngredientType(IngredientType type);

	IngredientType fetchIngredientType(String typeName);

	ArrayList<IngredientType> fetchAllIngredientTypes();

	void storeIngredients(Recipe recipe, ArrayList<Ingredient> ingredients);

	IngredientListHandler fetchIngredients(Recipe recipe);

}
