package ui.gui;

import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;

import java.util.Observable;
import java.util.Observer;

import appl.controllers.MainApp;
import domain.models.Recipe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import log.Log;


public class MenuController implements Controller,Observer {

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

	@FXML
	private Menu secondMenu;
	@FXML
	private MenuItem editRecipeItem;
	@FXML
	private MenuItem deleteRecipeItem;


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
		editRecipeItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				Recipe editRecipe = mainApp.getCurrentRecipe();
				try{
					if(editRecipe!=null){
						mainApp.showEditRecipeForm(mainApp.getCurrentRecipe());
					}
			}catch(NullPointerException npe){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("No recipe selected..");
				alert.setHeaderText("You have not selected a recipe for editing.");
				alert.setContentText("Please mark your selection in the list of recepies on the left side");
				alert.showAndWait();
			}
		}});
		deleteRecipeItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				Recipe deleteRecipe = mainApp.getCurrentRecipe();
				try{
					if(deleteRecipe!=null){
						recipeStorage.deleteRecipe(deleteRecipe);
					}
				}catch(NullPointerException npe){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("No recipe selected..");
					alert.setHeaderText("You have not selected a recipe to delete.");
					alert.setContentText("Please mark your selection in the list of recepies on the left side");
					alert.showAndWait();
				}
			}
		});

	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// Necessary evil...

	}





}
