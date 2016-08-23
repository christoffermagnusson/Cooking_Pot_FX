package storage.impl.util;

import domain.handlers.IngredientListHandler;
import domain.models.*;
import storage.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class IngredientStorageUtil {

  private IngredientListHandler currentHandler;

  public IngredientStorageUtil(IngredientListHandler currentHandler){
    this.currentHandler=currentHandler;
  }

  public boolean checkList(Ingredient toBeChecked){

    String checkString = String.format("SELECT ingredienttype FROM handler WHERE ingredienttype = '%s' AND listid = %d"
      ,toBeChecked.getType().getName()
      ,currentHandler.getId());
    try{
      ResultSet res = DBConnection.getInstance().execQuery(checkString);
        if(!res.isBeforeFirst()){ // key to the fucking problem!
        	System.out.println("false");
        return false;
      }
    }catch(StorageException se){
      se.printStackTrace();
    }catch(SQLException se){
    	se.printStackTrace();
    }
    System.out.println("true");
    return true;
  }

}
