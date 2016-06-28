package storage.impl;

import domain.models.*;
import storage.interfaces.ChefStorage;
import storage.factories.ChefStorageFactory;
import storage.interfaces.IngredientStorage;
import storage.factories.IngredientStorageFactory;
import java.util.ArrayList;


public class StorageMainTest {

  private ChefStorage chefStorage = ChefStorageFactory.getStorage();
  private IngredientStorage ingredientStorage = IngredientStorageFactory.getStorage();

  public static void main(String[] args){
    StorageMainTest smt = new StorageMainTest();
    smt.test();
  }

  void test(){
    
    testPrintList(chefStorage.fetchChefList());

    chefStorage.storeChef(new Chef("Kalle","Kuling"));

    chefStorage.fetchChef("Kuling");


  }
  void testPrintList(ArrayList<Chef> list){
    for(Chef chef : list){
      System.out.println(chef.toString());
    }
  }

}
