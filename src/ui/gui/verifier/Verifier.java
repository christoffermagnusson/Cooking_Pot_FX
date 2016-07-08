package ui.gui.verifier;

import java.util.ArrayList;

import javafx.scene.control.TextField;

public class Verifier {

	ArrayList<TextField> errorList = new ArrayList<TextField>();

	public boolean verify(ArrayList<TextField> inputFields){
		errorList.clear();
		for(TextField tf : inputFields){
			if(tf.getText().equals("")){
				errorList.add(tf);
				return false;
			}

		}
		return true;
	}
	public ArrayList<TextField> getErrorList(){
		return errorList;
	}

}
