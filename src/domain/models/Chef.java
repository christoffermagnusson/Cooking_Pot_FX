package domain.models;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Chef {


	private final StringProperty firstName;
	private final StringProperty lastName;

	public Chef(String firstName, String lastName){
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
	}

	public StringProperty firstNameProperty() {
		return this.firstName;
	}


	public String getFirstName() {
		return this.firstNameProperty().get();
	}


	public void setFirstName(String firstName) {
		this.firstNameProperty().set(firstName);
	}


	public StringProperty lastNameProperty() {
		return this.lastName;
	}


	public String getLastName() {
		return this.lastNameProperty().get();
	}


	public void setLastName(String lastName) {
		this.lastNameProperty().set(lastName);
	}

	public String toString(){
		return String.format("%s %s",this.firstName,this.lastName);
	}

}
