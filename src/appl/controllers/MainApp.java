package appl.controllers;

import java.io.IOException;

import domain.models.Recipe;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainApp extends Application{

  private Stage primaryStage;
  private BorderPane rootLayout;

  private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();

  public void start(Stage stage){
    this.primaryStage = stage;
    this.primaryStage.setTitle("Cooking_Pot_FX");

    initRootLayout();
  }

  public void initRootLayout(){
    try{
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("../../ui/gui/RootLayout.fxml"));
      rootLayout = (BorderPane) loader.load();

      Scene scene = new Scene(rootLayout);
      this.primaryStage.setScene(scene);
      this.primaryStage.show();

      showRecipeListView();
    }
    catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

  public void showRecipeListView(){
    try{
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("../../ui/gui/RecipeListView.fxml"));
      AnchorPane recipeListView = (AnchorPane) loader.load();

      rootLayout.setCenter(recipeListView);
    }
    catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

  public Stage getPrimaryStage(){
    return this.primaryStage;
  }
  public static void main(String[] args){
    launch(args);
  }
}
