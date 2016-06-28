package domain.models;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class IngredientType {

	private final StringProperty measurement;
	private final StringProperty name;

	public IngredientType(String name, String measurement){
		this.measurement = new SimpleStringProperty(measurement);
		this.name = new SimpleStringProperty(name);
	}

	public String getName(){
		return name.get();
	}
	public void setName(String name){
		this.name.set(name);
	}
	public StringProperty nameProperty(){
		return name;
	}
	public String measurement(){
		return measurement.get();
	}
	public void setMeasurement(String measurement){
		this.measurement.set(measurement);
	}
	public StringProperty measurementProperty(){
		return measurement;
	}



}
