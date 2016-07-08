package storage.interfaces;

import java.util.Observer;

public interface Observable {
	void addObserver(Observer obs);

	void deleteObservers();

	void notifyObservers(Object obj);
}
