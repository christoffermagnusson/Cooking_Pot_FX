package storage.interfaces;

import domain.models.Chef;
import java.util.ArrayList;

public interface ChefStorage {

	void storeChef(Chef chef);

	Chef fetchChef(String lastName);

	ArrayList<Chef> fetchChefList();

}
