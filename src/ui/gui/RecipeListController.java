package ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import appl.controllers.MainApp;
import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import storage.factories.*;
import storage.interfaces.*;
import storage.impl.*;


public class RecipeListController {

	@FXML
	private Label recipeNameLabel;
	@FXML
	private Label primaryIngredientLabel;
	@FXML
	private Label chefNameLabel;

	@FXML
	private ListView<Recipe> recipeList;
	@FXML
	private TableView<Ingredient> ingredientTable;
	@FXML
	private TableColumn<Ingredient,String> nameColumn;
	@FXML
	private TableColumn<Ingredient,String> amountColumn;
	@FXML
	private TextField filterTextField;

	private MainApp mainApp;
	private ChefStorage chefStorage = ChefStorageFactory.getStorage();
	private IngredientStorage ingredientStorage = IngredientStorageFactory.getStorage();
	private RecipeStorage recipeStorage = RecipeStorageFactory.getStorage();

	/**
	 * Underlying method should in future be able to take logged in user when displaying list in the view. In meantime using testdata..
	 * Testdata not working properly.. cant get list to display recipes from chef. Next time fix this!
	 */
	@FXML
	private void initialize(){
		ObservableList<Recipe> observableList = FXCollections.observableArrayList(recipeStorage.fetchRecipe(chefStorage.fetchChef("Magnusson")));
		// something goes wrong when fetching these..
		recipeList.setItems(observableList);
		ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().ingredientNameProperty());

	}
	//Tried making method but think it will not be needed..
	/*private ObservableList<Recipe> toObservableRecipeList(ArrayList<Recipe> recipeList){
		ObservableList<Recipe> obsRecipeList = FXCollections.observableArrayList();
	}*/

	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

}
