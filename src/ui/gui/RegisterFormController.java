package ui.gui;

import appl.controllers.MainApp;
import domain.models.Chef;
import domain.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import storage.interfaces.UserStorage;
import storage.interfaces.UtilStorage;

public class RegisterFormController implements Controller {

	private ChefStorage chefStorage;
	private UserStorage userStorage;
	private MainApp mainApp;

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;

	@FXML
	private Button registerButton;
	@FXML
	private Button closeButton;

	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage,
			UtilStorage utilStorage,UserStorage userStorage) {
		this.chefStorage=chefStorage;
		this.userStorage=userStorage;

	}
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

	@FXML
	private void initialize(){

	}

	@FXML
	private void handleRegisterButton(){
		Chef newChef = new Chef(firstNameField.getText()
								,lastNameField.getText());
		chefStorage.storeChef(newChef);
		Chef registredChef = chefStorage.fetchChef(lastNameField.getText());

		User user = new User(usernameField.getText()
							,passwordField.getText());
		user.setChef(registredChef);

		userStorage.storeUser(user);


	}

	@FXML
	private void handleCloseButton(){
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
