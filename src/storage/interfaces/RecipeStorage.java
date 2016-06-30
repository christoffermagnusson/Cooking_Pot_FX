package storage.interfaces;

import domain.models.*;
import java.util.ArrayList;

public interface RecipeStorage {

	void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage);

	void storeRecipe(Recipe recipe);

	Recipe fetchRecipe(String name);

	ArrayList<Recipe> fetchRecipe(Chef chef);

	ArrayList<Recipe> fetchRecipe(IngredientType type);

}
