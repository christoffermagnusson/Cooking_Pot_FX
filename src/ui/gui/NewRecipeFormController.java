package ui.gui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import appl.controllers.MainApp;
import domain.handlers.IngredientListHandler;
import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import log.Log;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import javafx.scene.control.TextArea;

public class NewRecipeFormController implements Controller,Observer{

	// Recipe details pane
	@FXML
	private TextField recipeNameField;
	@FXML
	private ComboBox<IngredientType> primaryIngredientBox;
	@FXML
	private Button detailsNewIngredientButton;

	// Ingredients pane
	@FXML
	private ListView<IngredientType> pickList;
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

	private ObservableList<IngredientType> ingredientTypeObsList;
	private ObservableList<Ingredient> ingredientObsList;
	private IngredientType primaryType;
	private IngredientListHandler ingredientListHandler = new IngredientListHandler();
	Log log = new Log();

	@FXML
	private void initialize(){
		ingredientObsList = FXCollections.observableArrayList();
		addedList.setItems(ingredientObsList);

		ingredientTypeObsList = FXCollections.observableArrayList();
		pickList.setItems(ingredientTypeObsList);

	}
	@Override
	public void update(Observable obs,Object obj){
		if(obs instanceof IngredientStorage){
			try{
				ArrayList<IngredientType> typeArray = (ArrayList<IngredientType>) obj;
				setIngredientTypeLists(typeArray);
			}catch(ClassCastException cce){
				ArrayList<Ingredient> ingredientArray = (ArrayList<Ingredient>) obj;
			}
		}
		else if(obs instanceof ChefStorage){
			// not needed perhaps in this occasion?
		}
		else if(obs instanceof RecipeStorage){
			// not needed perhaps in this occasion?
		}
	}

	public void initComponents(){
		setIngredientTypeLists(ingredientStorage.fetchAllIngredientTypes());


	}
	private void setIngredientTypeLists(ArrayList<IngredientType> typeArray){
		ingredientTypeObsList.clear();
		for(IngredientType it : typeArray){
			if(!primaryIngredientBox.getItems().contains(it)){
			primaryIngredientBox.getItems().add(it);
			}
			ingredientTypeObsList.add(it);
		}

	}
	private void setIngredientList(ArrayList<Ingredient> ingredientArray){
		for(Ingredient i : ingredientArray){
			if(!ingredientObsList.contains(i)){
				ingredientObsList.add(i);
			}
		}
	}





	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}



	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;

	}
	// ingredienthandlers
	@FXML
	private void handleNewIngredientTypeButton(){
		mainApp.showNewIngredientTypeDialog();
	}
	@FXML
	private void handleAddToRecipeButton(){
		IngredientType tmpType = pickList.getSelectionModel().getSelectedItem();
		Ingredient ingredient = new Ingredient(tmpType,0);

		if(ingredientListHandler.checkList(ingredient)==true){
		ingredientListHandler.addIngredient(ingredient);

		mainApp.showAddToRecipeDialog(ingredientListHandler);
		setIngredientList(ingredientListHandler.getIngredientList());
		}
		else{
			log.write(String.format("%s already added to recipe",ingredient)); // add dialog with user!
		}

	}

	// lowest buttonbar handlers
	@FXML
	private void handleBackButton(){
		mainApp.showRecipeListView();
	}
	@FXML
	private void handleSaveButton(){
		Recipe recipe = new Recipe(recipeNameField.getText()
									,mainApp.getSession().getChef()
									,primaryIngredientBox.getValue()
									,ingredientListHandler 				// uses instance variable ..maybe not do that?
									,descriptionArea.getText());

		ingredientStorage.storeIngredients(recipe,recipe.getRecipeIngredientListHandler().getIngredientList());
		recipeStorage.storeRecipe(recipe);
	}

}
