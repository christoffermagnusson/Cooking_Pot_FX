package ui.gui;

import appl.controllers.MainApp;
import domain.models.Ingredient;
import domain.models.IngredientType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import javafx.scene.control.TextArea;

public class NewRecipeFormController implements Controller{

	// Recipe details pane
	@FXML
	private TextField recipeNameField;
	@FXML
	private ComboBox<IngredientType> primaryIngredientBox;
	@FXML
	private Button detailsNewIngredientButton;

	// Ingredients pane
	@FXML
	private ListView<Ingredient> pickList;
	@FXML
	private ListView<Ingredient> addedList;
	@FXML
	private Button addToRecipeButton;
	@FXML
	private Button ingredientNewButton;
	@FXML
	private Button removeButton;

	// Description pane
	@FXML
	private TextArea descriptionArea;

	// Lower buttonbar
	@FXML
	private Button saveRecipeButton;
	@FXML
	private Button undoButton;
	@FXML
	private Button backButton;

	private MainApp mainApp;
	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;

	@FXML
	private void initialize(){

	}



	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}



	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;

	}

}
