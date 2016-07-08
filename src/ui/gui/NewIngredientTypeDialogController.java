package ui.gui;

import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import ui.gui.verifier.Verifier;

import java.util.ArrayList;

import appl.controllers.MainApp;
import domain.models.IngredientType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class NewIngredientTypeDialogController implements Controller {

	@FXML
	private TextField nameField;
	@FXML
	private TextField measurementField;
	@FXML
	private Label nameFieldErrorLabel;
	@FXML
	private Label measurementFieldErrorLabel;
	@FXML
	private Label errorLabel;
	private ArrayList<TextField> fieldArray = new ArrayList<TextField>();

	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;
	private MainApp mainApp;
	private Verifier ver = new Verifier();


	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;

	}

	@FXML
	private void initialize(){
		fieldArray.add(nameField); fieldArray.add(measurementField);
	}

	@FXML
	private void handleAddButton(){
		hideLabels();
		if(ver.verify(fieldArray)==true){
		ingredientStorage.storeIngredientType(new IngredientType(nameField.getText(),measurementField.getText()));
		}
		else{
			errorLabel.setVisible(true);
			for(TextField tf : ver.getErrorList()){
				if(tf.equals(nameField)){
					nameFieldErrorLabel.setVisible(true);
				}
				else if(tf.equals(measurementField)){
					measurementFieldErrorLabel.setVisible(true);
				}
			}
		}
	}
	private void hideLabels(){
		errorLabel.setVisible(false);
		nameFieldErrorLabel.setVisible(false);
		measurementFieldErrorLabel.setVisible(false);
	}

	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

}
