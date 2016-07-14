package storage.impl.test;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import storage.impl.*;
import storage.factories.*;
import storage.interfaces.*;
import storage.util.*;
import log.Log;

/**
*   Class used for testing purposing if db works as it should. Proper work needs to be done with id. 
*   setting id as storing new lists etc...
*/
public class DBTester{
  
  public void initAll(){
    //initChefs();
    //initIngredientTypes();
   
  }

  private void initChefs(){
    String filePath = String.format("%s/dbresources/chefinit.txt",new File(".").getAbsolutePath());
    try{
      Scanner fileScan = new Scanner(new File(filePath)).useDelimiter(";");
      while(fileScan.hasNext()){
        String testUpdate = String.format("INSERT INTO chef VALUES('%s','%s')"
          ,fileScan.next(),fileScan.next());
        DBConnection.getInstance().update(testUpdate);

      }
    }
    catch(FileNotFoundException fnfe){
      Log.write(fnfe.getMessage());
    }
    catch(StorageException se){
      Log.write(se.getMessage());
    }
  }
  private void initIngredientTypes(){
    String filepath = String.format("%s/dbresources/ingredientinit.txt",new File(".").getAbsolutePath());
    try{
      Scanner filescan = new Scanner(new  File(filepath)).useDelimiter(";");
      while(filescan.hasNext()){
        String testupdate = String.format("INSERT INTO ingredienttype VALUES('%s','%s')"
          ,filescan.next(),filescan.next());
        DBConnection.getInstance().update(testupdate);
      }
    }
    catch(FileNotFoundException fnfe){
      Log.write(fnfe.getMessage());
    }
    catch(StorageException se){
      Log.write(se.getMessage());
    }
  }
}