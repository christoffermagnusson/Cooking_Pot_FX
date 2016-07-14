package storage.impl.test;

import domain.handlers.IngredientListHandler;
import domain.models.*;
import storage.interfaces.ChefStorage;
import storage.factories.ChefStorageFactory;
import storage.interfaces.IngredientStorage;
import storage.factories.IngredientStorageFactory;
import storage.interfaces.RecipeStorage;
import storage.factories.RecipeStorageFactory;
import storage.impl.*;
import storage.util.DBConnection;
import storage.util.StorageException;
import log.Log;
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
    
  DBTester test = new DBTester();
  test.initAll(); // used once to init testdata to db
  IngredientStorageImpl tester = new IngredientStorageImpl();
  //tester.storeIngredientType(new IngredientType("Carrot","pieces"));
  //IngredientType type = tester.fetchIngredientType("Dorre");
  ArrayList<IngredientType> array = tester.fetchAllIngredientTypes();
  for(IngredientType it : array){
    Log.write(it.toString());
  }


  }
  void testPrintList(ArrayList<Ingredient> list){
    for(Ingredient i : list){
      System.out.println(i.toString());
    }
  }

}
