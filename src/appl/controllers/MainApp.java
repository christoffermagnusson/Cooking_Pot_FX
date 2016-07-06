package appl.controllers;

import java.io.IOException;

import domain.models.Recipe;
import domain.models.Session;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import storage.factories.ChefStorageFactory;
import storage.factories.IngredientStorageFactory;
import storage.factories.RecipeStorageFactory;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import ui.gui.RecipeListController;
import ui.gui.Controller;
import ui.gui.MenuController;
import ui.gui.NewRecipeFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainApp extends Application{

  private Stage primaryStage;
  private BorderPane rootLayout;

  private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();

  // starting up storages here and only here.
  	private ChefStorage chefStorage = ChefStorageFactory.getStorage();
	private IngredientStorage ingredientStorage = IngredientStorageFactory.getStorage();
	private RecipeStorage recipeStorage = RecipeStorageFactory.getStorage();
	private Session session;

	private void initStorages(Controller controller){
		controller.setStorages(this.chefStorage,this.ingredientStorage,this.recipeStorage);
	}

  public void start(Stage stage){
	  System.setProperty("glass.accessible.force", "false"); // temporary workaround because of bug when using a touchscreen computer with windows 10..
    this.primaryStage = stage;
    this.primaryStage.setTitle("Cooking_Pot_FX");

    recipeStorage.setStorages(chefStorage,ingredientStorage); // not sure if this is needed later on..inits storages so that this one works properly
    session = new Session(chefStorage.fetchChef("Magnusson"));
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

      MenuController controller = loader.getController();
      controller.setMainApp(this);
      initStorages(controller);

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

      RecipeListController controller = loader.getController();
      controller.setMainApp(this);
      initStorages(controller);

      controller.setRecipeListItems(session.getChef());


    }
    catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

  public void showNewRecipeForm(){
	  try{
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("../../ui/gui/NewRecipeForm.fxml"));
		  AnchorPane newRecipeForm = (AnchorPane) loader.load();

		  rootLayout.setCenter(newRecipeForm);

		  NewRecipeFormController controller = loader.getController();
		  controller.setMainApp(this);
		  initStorages(controller);

		  controller.initPrimaryIngredientList();
	  }
	  catch(IOException ioe){
		  ioe.printStackTrace();
	  }
  }

  public Stage getPrimaryStage(){
    return this.primaryStage;
  }
  public Session getSession(){
	  return session;
  }
  public static void main(String[] args){
    launch(args);
  }
}
