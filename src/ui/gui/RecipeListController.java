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


public class RecipeListController implements Controller{

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
	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;

	public void setStorages(ChefStorage chefStorage,IngredientStorage ingredientStorage,RecipeStorage recipeStorage){
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;
	}

	/**
	 * Underlying method should in future be able to take logged in user when displaying list in the view. In meantime using testdata..
	 * Testdata now working properly. Will now attempt next step to create new recipes.
	 */
	@FXML
	private void initialize(){
		//recipeStorage.setStorages(chefStorage,ingredientStorage); // giving recipestorage same references to storages as this class.. might be handy to abstract another layer upward
		ObservableList<Recipe> observableList = FXCollections.observableArrayList(recipeStorage.fetchRecipe(chefStorage.fetchChef("Blackby")));
		recipeList.setItems(observableList);
		recipeList.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> showRecipeDetails(newValue));


		ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().ingredientNameProperty());

	}

	private void showRecipeDetails(Recipe recipe){
		if(recipe != null){
			recipeNameLabel.setText(recipe.getRecipeName());
			primaryIngredientLabel.setText(recipe.getRecipePrimaryIngredientType().toString());
			chefNameLabel.setText(recipe.getRecipeChef().toString());

			// here goes ingredientlist associated with recipe as well.
		}
		else{
			recipeNameLabel.setText("");
			primaryIngredientLabel.setText("");
			chefNameLabel.setText("");
		}
	}


	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

}
