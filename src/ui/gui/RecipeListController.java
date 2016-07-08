package ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import appl.controllers.MainApp;
import domain.models.Chef;
import domain.models.Ingredient;
import domain.models.IngredientType;
import domain.models.Recipe;
import storage.factories.*;
import storage.interfaces.*;
import storage.impl.*;
import java.util.Observer;
import java.util.Observable;


public class RecipeListController implements Controller,Observer{

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
	private TableColumn<Ingredient,Integer> amountColumn;
	@FXML
	private TableColumn<Ingredient,String> measurementColumn;
	@FXML
	private TextField filterTextField;
	@FXML
	private TextArea descriptionArea;
	@FXML
	private ImageView imageView;

	private MainApp mainApp;
	private ChefStorage chefStorage;
	private IngredientStorage ingredientStorage;
	private RecipeStorage recipeStorage;
	private ObservableList<Recipe> recipeObsList;
	private ObservableList<Ingredient> ingredientObsList;

	public void setStorages(ChefStorage chefStorage,IngredientStorage ingredientStorage,RecipeStorage recipeStorage){
		this.chefStorage=chefStorage;
		this.ingredientStorage=ingredientStorage;
		this.recipeStorage=recipeStorage;

	}
	public void setRecipeListItems(Chef chef){
		recipeObsList = FXCollections.observableArrayList(recipeStorage.fetchRecipe(chef));
		recipeList.setItems(recipeObsList);

	}
	/**
	 * Perhaps a bit redundant because of the initialize method that is called when showing this window.. but we will see
	 */
  public void update(Observable obs,Object obj){
    setRecipeListItems(mainApp.getSession().getChef());
  }

	/**
	 * Underlying method should in future be able to take logged in user when displaying list in the view. In meantime using testdata..
	 * Adding recipes now work in realtime.. just need to work out how to integrate ingredientlists
	 */
	@FXML
	private void initialize(){
		recipeObsList = FXCollections.observableArrayList();
		recipeList.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> showRecipeDetails(newValue));

		ingredientObsList = FXCollections.observableArrayList();
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().ingredientNameProperty());
		amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
		measurementColumn.setCellValueFactory(cellData -> cellData.getValue().measurementProperty());

		ingredientTable.setItems(ingredientObsList);


	}

	private void showRecipeDetails(Recipe recipe){
		if(recipe != null){
			ingredientObsList.clear();

			recipeNameLabel.setText(recipe.getRecipeName());
			primaryIngredientLabel.setText(recipe.getRecipePrimaryIngredientType().toString());
			chefNameLabel.setText(recipe.getRecipeChef().toString());

			descriptionArea.setText(recipe.getDescription());

			for(Ingredient i : recipe.getRecipeIngredientListHandler().getIngredientList()){
				ingredientObsList.add(i);
			}

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
