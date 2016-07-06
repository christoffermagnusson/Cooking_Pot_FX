package ui.gui;

import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import appl.controllers.MainApp;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import log.Log;


public class MenuController implements Controller {

	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;

	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu firstMenu;
	@FXML
	private MenuItem closeItem;
	@FXML
	private MenuItem newRecipeItem;

	private MainApp mainApp;
	Log log = new Log();


	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;

	}
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

	@FXML
	private void initialize(){
		newRecipeItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				mainApp.showNewRecipeForm();
			}
		});
		closeItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				log.write("Closing program...");
				Platform.exit();
			}
		});

	}





}
