package storage.interfaces;

import domain.models.*;
import java.util.ArrayList;

public interface RecipeStorage extends Observable{

	void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage);

	void storeRecipe(Recipe recipe);

	Recipe fetchRecipe(String name);

	ArrayList<Recipe> fetchRecipe(Chef chef);

	ArrayList<Recipe> fetchRecipe(IngredientType type);

	void deleteRecipe(Recipe recipe);

	int getId(Recipe recipe);

	void setId(ArrayList<Recipe> recipes);

}
