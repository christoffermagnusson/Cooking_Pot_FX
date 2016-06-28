package storage.interfaces;

import domain.models.IngredientType;
import domain.models.Ingredient;
import domain.models.Recipe;
import java.util.ArrayList;

public interface IngredientStorage {

	void storeIngredientType(IngredientType type);

	IngredientType fetchIngredientType(String typeName);

	void storeIngredients(Recipe recipe, ArrayList<Ingredient> ingredients);

	ArrayList<Ingredient> fetchIngredients(Recipe recipe);

}
