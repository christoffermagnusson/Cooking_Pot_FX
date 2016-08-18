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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import log.Log;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;

public class EditRecipeFormController implements Controller, Observer {

	@FXML
	private Button saveChangesButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button backButton;

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
	@FXML
	private Button editAmountButton;

	@FXML
	private TextField recipeNameField;
	@FXML
	private ComboBox<IngredientType> primaryIngredientBox;
	@FXML
	private Button detailsNewIngredientButton;

	// Description pane
	@FXML
	private TextArea descriptionArea;

	private MainApp mainApp;
	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;
	private Recipe recipe;

	private ObservableList<IngredientType> ingredientTypeObsList;
	private ObservableList<Ingredient> ingredientObsList;
	private IngredientType primaryType;
	private IngredientListHandler ingredientListHandler;

	Log log = new Log();

	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof IngredientStorage){
			try{
				ArrayList<IngredientType> typeArray = (ArrayList<IngredientType>) obj;
				setIngredientTypeLists(typeArray);
			}catch(ClassCastException cce){
				ArrayList<Ingredient> ingredientArray = (ArrayList<Ingredient>) obj;
			}
		}
	}

	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;

	}
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

	public void setRecipe(Recipe recipe){
		this.recipe=recipe;
	}
	public void initComponents(){
		setIngredientListHandler();
		recipeNameField.setText(recipe.getRecipeName());
		setPrimaryIngredient();

		setIngredientTypeLists(ingredientStorage.fetchAllIngredientTypes());

		ArrayList<Ingredient> ingredientArray = ingredientStorage.fetchIngredients(recipe).getIngredientList();
		setIngredientList(ingredientArray);

		descriptionArea.setText(recipe.getDescription());  ///// CONTINUE HERE !!, CONTINUE with displaying and passing recipe for editing.
	}
	private void clearComponents(){
		recipeNameField.setText("");
		primaryIngredientBox.setValue(null);
		setIngredientList(null);
		descriptionArea.setText("");
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
		ingredientObsList.clear();
		for(Ingredient i : ingredientArray){
			if(!ingredientObsList.contains(i)){
				ingredientObsList.add(i);
			}
		}
	}
	private void setPrimaryIngredient(){
		primaryIngredientBox.setValue(recipe.getRecipePrimaryIngredientType());
	}
	private void setIngredientListHandler(){
		this.ingredientListHandler=recipe.getRecipeIngredientListHandler();
	}

	@FXML
	private void initialize(){
		ingredientTypeObsList = FXCollections.observableArrayList();
		pickList.setItems(ingredientTypeObsList);

		ingredientObsList = FXCollections.observableArrayList();
		addedList.setItems(ingredientObsList);
	}

	@FXML
	private void handleNewIngredientTypeButton(){
		mainApp.showNewIngredientTypeDialog();
	}
	@FXML
	private void handleRemoveFromRecipeButton(){
		Ingredient toBeRemoved = addedList.getSelectionModel().getSelectedItem();
		ingredientListHandler.deleteIngredient(toBeRemoved);
		setIngredientList(ingredientListHandler.getIngredientList());

	}
	@FXML
	private void handleAddToRecipeButton(){
		IngredientType tmpType = pickList.getSelectionModel().getSelectedItem();
		Ingredient ingredient = new Ingredient(tmpType,0);

		if(ingredientListHandler.checkList(ingredient)==true){
		ingredientListHandler.addIngredient(ingredient);

		mainApp.showAddToRecipeDialog(ingredientListHandler,null);
		setIngredientList(ingredientListHandler.getIngredientList());
		}
		else{
			log.write(String.format("%s already added to recipe",ingredient)); // add dialog with user!
		}
	}
	@FXML
	private void handleIngredientAmountButton(){
		try{
			Ingredient toBeEdited = addedList.getSelectionModel().getSelectedItem();
			mainApp.showAddToRecipeDialog(ingredientListHandler,toBeEdited);
		}
		catch(NullPointerException npe){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No selected item..");
			alert.setHeaderText("Please mark your selection to proceed with editing.");
			alert.showAndWait();
		}
	}
	@FXML
	private void handleSaveChangesButton(){
		recipeStorage.storeRecipe(recipe);
		ingredientStorage.storeIngredients(recipe,ingredientListHandler.getIngredientList());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Recipe updated");
		alert.setHeaderText(String.format("Recipe for %s was successfully updated",recipe.getRecipeName()));
		alert.showAndWait();

	}
	@FXML
	private void handleDeleteRecipeButton(){
		recipeStorage.deleteRecipe(recipe);
		clearComponents();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Recipe deleted");
		alert.setHeaderText(String.format("Recipe for %s was successfully deleted!",recipe.getRecipeName()));
		alert.showAndWait();

		mainApp.setCurrentRecipe(null);
		mainApp.showRecipeListView();
	}
	@FXML
	private void handleBackButton(){
		mainApp.setCurrentRecipe(null);
		mainApp.showRecipeListView();
	}


}
