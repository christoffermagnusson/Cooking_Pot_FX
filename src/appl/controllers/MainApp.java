package appl.controllers;

import java.io.IOException;
import storage.util.DBConnection;
import domain.handlers.IngredientListHandler;
import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import domain.models.Session;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import log.Log;
import storage.factories.ChefStorageFactory;
import storage.factories.IngredientStorageFactory;
import storage.factories.RecipeStorageFactory;
import storage.factories.UserStorageFactory;
import storage.factories.UtilStorageFactory;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import storage.interfaces.UserStorage;
import storage.interfaces.UtilStorage;
import ui.gui.RecipeListController;
import ui.gui.RegisterFormController;
import ui.gui.AddToRecipeDialogController;
import ui.gui.Controller;
import ui.gui.EditRecipeFormController;
import ui.gui.LoginFormController;
import ui.gui.MenuController;
import ui.gui.NewIngredientTypeDialogController;
import ui.gui.NewRecipeFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Observer;

public class MainApp extends Application{

  private Stage primaryStage;
  private BorderPane rootLayout;

  private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();

  // starting up storages here and only here.
  	private ChefStorage chefStorage = ChefStorageFactory.getStorage();
	private IngredientStorage ingredientStorage = IngredientStorageFactory.getStorage();
	private RecipeStorage recipeStorage = RecipeStorageFactory.getStorage();
	private UtilStorage utilStorage = UtilStorageFactory.getStorage();
	private UserStorage userStorage = UserStorageFactory.getStorage();
	private Session session;
	Log log = new Log();

	private void initStorages(Controller controller){
		controller.setStorages(this.chefStorage,this.ingredientStorage,this.recipeStorage,this.utilStorage,this.userStorage);
		try{
    this.chefStorage.addObserver((Observer) controller);
    this.ingredientStorage.addObserver((Observer) controller);
    this.recipeStorage.addObserver((Observer) controller);
	}catch(ClassCastException cce){
		log.write("Class is not observer");
	}
	}

  public void start(Stage stage){


	  System.setProperty("glass.accessible.force", "false"); // temporary workaround because of bug when using a touchscreen computer with windows 10..
    this.primaryStage = stage;
    this.primaryStage.setTitle("Cooking_Pot_FX");

    recipeStorage.setStorages(chefStorage,ingredientStorage); // not sure if this is needed later on..inits storages so that this one works properly
    initRootLayout();

  }


  public void initRootLayout(){
    try{
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("../../ui/gui/RootLayout.fxml"));
      rootLayout = (BorderPane) loader.load();

      Scene scene = new Scene(rootLayout);
      this.primaryStage.setMaximized(true);
      this.primaryStage.setScene(scene);
      this.primaryStage.show();

      MenuController controller = loader.getController();
      controller.setMainApp(this);
      initStorages(controller);

      showLoginFormView();

    }
    catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

  public void showLoginFormView(){
	  try{
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("../../ui/gui/LoginForm.fxml"));

		  AnchorPane loginForm = (AnchorPane) loader.load();

		  rootLayout.setCenter(loginForm);

		  LoginFormController controller = loader.getController();
		  controller.setMainApp(this);
		  initStorages(controller);
	  }
	  catch(IOException ioe){
		  ioe.printStackTrace();
	  }
  }

  public void showRegisterFormView(){
	  try{
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("../../ui/gui/RegisterForm.fxml"));

		  AnchorPane registerForm = (AnchorPane) loader.load();

		  Stage tmpStage = new Stage();
		  tmpStage.setTitle("Register new user account");
		  Scene tmpScene = new Scene(registerForm);
		  tmpStage.setScene(tmpScene);
		  tmpStage.show();

		  RegisterFormController controller = loader.getController();
		  controller.setMainApp(this);
		  initStorages(controller);
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
      controller.initListListener();


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

		  controller.initComponents();
	  }
	  catch(IOException ioe){
		  ioe.printStackTrace();
	  }
  }

  public void showEditRecipeForm(Recipe recipe){
	  try{
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("../../ui/gui/EditRecipeForm.fxml"));
		  AnchorPane editRecipeForm = (AnchorPane) loader.load();

		  rootLayout.setCenter(editRecipeForm);

		  EditRecipeFormController controller = loader.getController();
		  controller.setMainApp(this);
		  initStorages(controller);

		  controller.setRecipe(recipe);
		  controller.initComponents();
	  }
	  catch(IOException ioe){
		  ioe.printStackTrace();
	  }
  }
  public void showNewIngredientTypeDialog(){
	  try{
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("../../ui/gui/NewIngredientTypeDialog.fxml"));
		  AnchorPane newIngredientTypeDialog = (AnchorPane) loader.load();

		  Stage tmpStage = new Stage();
		  tmpStage.setTitle("Create new ingredient type..");
		  Scene tmpScene = new Scene(newIngredientTypeDialog);
		  tmpStage.setScene(tmpScene);
		  tmpStage.show();

		  NewIngredientTypeDialogController controller = loader.getController();
		  controller.setMainApp(this);
		  initStorages(controller);
	  }
	  catch(IOException ioe){
		  ioe.printStackTrace();
	  }
  }

  public void showAddToRecipeDialog(IngredientListHandler handler, Ingredient ingredient){
	  try{
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("../../ui/gui/AddToRecipeDialog.fxml"));
		  AnchorPane addToRecipeDialog = (AnchorPane) loader.load();

		  Stage tmpStage = new Stage();
		  tmpStage.setTitle("Specify amount to add..");
		  Scene tmpScene = new Scene(addToRecipeDialog);
		  tmpStage.setScene(tmpScene);


		  AddToRecipeDialogController controller = loader.getController();
		  controller.setMainApp(this);
		  controller.setHandler(handler);
		  controller.initComponents(ingredient);
		  initStorages(controller);
		  tmpStage.showAndWait(); // Thread waits until dialog is finished.

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
  public void setSession(Session session){
	  this.session=session;
  }
  public Recipe getCurrentRecipe(){
	  return session.getCurrentRecipe();
  }
  public void setCurrentRecipe(Recipe recipe){
	  session.setCurrentRecipe(recipe);
  }
  public static void main(String[] args){
    launch(args);
  }
}
