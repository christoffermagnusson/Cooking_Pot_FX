package domain.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Ingredient {

	private final ObjectProperty<IngredientType> type;
	private final IntegerProperty amount;


	public Ingredient(IngredientType type, int amount){
		this.type = new SimpleObjectProperty<IngredientType>(type);
		this.amount = new SimpleIntegerProperty(amount);
	}

	public IngredientType getType(){
		return type.get();
	}
	public void setType(IngredientType type){
		this.type.set(type);
	}
	public ObjectProperty<IngredientType> typeProperty(){
		return type;
	}
	public int getAmount(){
		return amount.get();
	}
	public void setAmount(int amount){
		this.amount.set(amount);
	}
	public IntegerProperty amountProperty(){
		return amount;
	}

}
