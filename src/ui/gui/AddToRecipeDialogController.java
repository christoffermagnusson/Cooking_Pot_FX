package ui.gui;

import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import storage.interfaces.UtilStorage;
import ui.gui.verifier.Verifier;

import java.util.ArrayList;

import appl.controllers.MainApp;
import domain.handlers.IngredientListHandler;
import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;

public class AddToRecipeDialogController extends Thread implements Controller{

	@FXML
	private Label amountLabel;
	@FXML
	private Label typeLabel;
	@FXML
	private Slider amountSlider;
	@FXML
	private Spinner amountSpinner;
	@FXML
	private Button addButton;
	@FXML
	private Button backButton;

	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;
	private UtilStorage utilStorage;

	private MainApp mainApp;
	private Verifier ver = new Verifier(); // not needed here maybe? clean this later if redundant.

	private IngredientListHandler handler;
	private Ingredient currentIngredient;

	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage, UtilStorage utilStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;
		this.utilStorage=utilStorage;

	}

	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	public void setHandler(IngredientListHandler handler){
		this.handler=handler;
	}

	public void initComponents(Ingredient ingredient){
		if(ingredient==null){
		currentIngredient = handler.getLatestAdded();
		}
		else{currentIngredient=ingredient;}

		amountSpinner.getValueFactory().setValue(currentIngredient.getAmount());
		amountSlider.valueProperty().setValue(currentIngredient.getAmount()); // maybe needs cast to Double
		amountLabel.setText(String.format("Specify amount of %s",currentIngredient.getType().getName()));
		typeLabel.setText(currentIngredient.getType().measurement());

		Stage stage = (Stage) addButton.getScene().getWindow();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			public void handle(WindowEvent we){
				if(handler.getId()==0){
				handler.deleteLatest();
			}}
		});
	}

	@FXML
	private void initialize(){
		amountSpinner.setValueFactory(new IntegerSpinnerValueFactory(1,1000)); // at the moment default values 1 -> 1000 ; perhaps need an alghorithm here??
		amountSlider.setMin(1);	amountSlider.setMax(1000);

		amountSlider.valueProperty().addListener(new ChangeListener<Number>(){
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val,Number new_val){
				handleAmountSlider(new_val.intValue());
			}
		});

	}

	/**
	 * Method uses handler associated with recipe that is being created to get the latest added ingredient to the list
	 * so that the user can set the amount of ingredients according to the specified type.
	 */
	@FXML
	private void handleAddButton(){
		/*Ingredient toBeAdded = handler.getLatestAdded(); // get the latest added ingredient
		toBeAdded.setAmount((Integer) amountSpinner.getValue()); // sets the value*/
		currentIngredient.setAmount((Integer) amountSpinner.getValue());

		Stage stage = (Stage) addButton.getScene().getWindow();
		stage.close();
	}
	@FXML
	private void handleBackButton(){
		if(handler.getId()==0){
		handler.deleteLatest(); // deletes this so that it will not be added to the recipe
		}
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	private void handleAmountSlider(int value){
		amountSpinner.getValueFactory().setValue(value);
	}



}
