package storage.factories;

import storage.interfaces.UserStorage;
import storage.impl.UserStorageImpl;

public class UserStorageFactory {

	public static UserStorage getStorage(){
		return new UserStorageImpl();
	}

}
