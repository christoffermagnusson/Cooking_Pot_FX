package storage.impl;

import domain.handlers.IngredientListHandler;
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

    recipeStorage.setStorages(chefStorage,ingredientStorage);

    recipeStorage.storeRecipe(new Recipe("Vanilla Ice Cream",chefStorage.fetchChef("Ramsay")
      ,ingredientStorage.fetchIngredientType("Milk"),new IngredientListHandler(),"Cook it like you mean it"));

    Recipe test = recipeStorage.fetchRecipe("Vanilla Ice Cream");
    ArrayList<Ingredient> testList = new ArrayList<Ingredient>();
    testList.add(new Ingredient(ingredientStorage.fetchIngredientType("Milk"),200));
    testList.add(new Ingredient(ingredientStorage.fetchIngredientType("Minced meat"),100));
    ingredientStorage.storeIngredients(test,testList);
    //testPrintList(ingredientStorage.fetchIngredients(test).getIngredientList());

  }
  void testPrintList(ArrayList<Ingredient> list){
    for(Ingredient i : list){
      System.out.println(i.toString());
    }
  }

}
