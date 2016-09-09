package ui.gui;

import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import storage.interfaces.UserStorage;
import storage.interfaces.UtilStorage;
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
	@FXML
	private Label successLabel;
	private ArrayList<TextField> fieldArray = new ArrayList<TextField>();

	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;
	private UtilStorage utilStorage;
	private MainApp mainApp;
	private Verifier ver = new Verifier();


	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage,UtilStorage utilStorage,UserStorage userStorage) {
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;
		this.utilStorage=utilStorage;

	}

	@FXML
	private void initialize(){
		fieldArray.add(nameField); fieldArray.add(measurementField);
	}

	@FXML
	private void handleAddButton(){
		hideLabels();
		successLabel.setVisible(false);
		if(ver.verify(fieldArray)==true){
		ingredientStorage.storeIngredientType(new IngredientType(nameField.getText(),measurementField.getText()));
		successLabel.setText(String.format("Ingredienttype %s added.",nameField.getText()));
		successLabel.setVisible(true);
		nameField.setText("");
		measurementField.setText("");
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
