package storage.interfaces;

import domain.models.Chef;
import java.util.ArrayList;

public interface ChefStorage extends Observable{

	void storeChef(Chef chef);

	Chef fetchChef(String lastName);

	Chef fetchUserChef(String username);

	ArrayList<Chef> fetchChefList();

	int getLatestID();

}
