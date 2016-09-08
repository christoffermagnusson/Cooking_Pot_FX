package ui.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import appl.controllers.MainApp;
import domain.models.Chef;
import domain.models.Session;
import domain.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import storage.factories.UserStorageFactory;
import storage.impl.util.UserStorageUtil;
import storage.interfaces.ChefStorage;
import storage.interfaces.IngredientStorage;
import storage.interfaces.RecipeStorage;
import storage.interfaces.UserStorage;
import storage.interfaces.UtilStorage;

public class LoginFormController implements Controller {

	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;
	private UtilStorage utilStorage;

	private UserStorage userStorage = UserStorageFactory.getStorage();
	private UserStorageUtil userStorageUtil = new UserStorageUtil();

	private MainApp mainApp;

	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;

	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private Label errorLabel;


	@Override
	public void setStorages(ChefStorage chefStorage, IngredientStorage ingredientStorage, RecipeStorage recipeStorage,
			UtilStorage utilStorage) {
		// no need for these?

	}

	@FXML
	private void initialize(){
		//TODO adding something here?
	}

	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}

	@FXML
	void handleLoginButton(){
		hideErrorLabels();
		String username = usernameField.getText();
		String password = passwordField.getText();

		ArrayList<String> errorList = new ArrayList<String>();
		HashMap<Boolean,String> validatedMap = userStorageUtil.validate(username,password);
		for(Map.Entry<Boolean,String> entry : validatedMap.entrySet()){
			if(entry.getKey()==false){
				errorList.add(entry.getValue());
			}
		}
		if(errorList.isEmpty()){
			User user = userStorage.getUser(username,password);
			startNewSession(user);
			mainApp.showRecipeListView();

		}
		else{
			if(errorList.contains("username") && errorList.contains("password")){
				showErrorLabel("username"); showErrorLabel("password");
			}
			else if(errorList.contains("username") && !errorList.contains("password")){
				showErrorLabel("username");
			}
			else{
				showErrorLabel("password");
			}

		}
		}
	void startNewSession(User user){
		Chef currentChef = chefStorage.fetchUserChef(user.getUsername());
		user.setChef(currentChef);

		Session currentSession = new Session(user);

		mainApp.setSession(currentSession);
	}


	private void showErrorLabel(String labelToShow){
		if(labelToShow.equals("username")){
			usernameLabel.setVisible(true);
		}
		else if(labelToShow.equals("password")){
			passwordLabel.setVisible(true);
		}
		errorLabel.setVisible(true);
	}
	private void hideErrorLabels(){
		usernameLabel.setVisible(false); passwordLabel.setVisible(false);
		errorLabel.setVisible(false);
	}





}
