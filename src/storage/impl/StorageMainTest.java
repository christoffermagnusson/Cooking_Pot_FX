package storage.impl;

import domain.models.*;
import storage.interfaces.ChefStorage;
import storage.factories.ChefStorageFactory;
import storage.interfaces.IngredientStorage;
import storage.factories.IngredientStorageFactory;
import storage.interfaces.RecipeStorage;
import storage.factories.RecipeStorageFactory;
import java.util.ArrayList;


public class StorageMainTest {

  private ChefStorage chefStorage = ChefStorageFactory.getStorage();
  private IngredientStorage ingredientStorage = IngredientStorageFactory.getStorage();
  private RecipeStorage recipeStorage = RecipeStorageFactory.getStorage();

  public static void main(String[] args){
    StorageMainTest smt = new StorageMainTest();
    smt.test();
  }

  void test(){
    
    testPrintList(recipeStorage.fetchRecipe(ingredientStorage.fetchIngredientType("Minced meat")));
    testPrintList(recipeStorage.fetchRecipe(chefStorage.fetchChef("Blackby")));

    recipeStorage.storeRecipe(new Recipe("Vanilla Ice Cream",chefStorage.fetchChef("Ramsay")
      ,ingredientStorage.fetchIngredientType("Milk")));

    recipeStorage.fetchRecipe("Vanilla Ice Cream");


  }
  void testPrintList(ArrayList<Recipe> list){
    for(Recipe r : list){
      System.out.println(r.toString());
    }
  }

}
