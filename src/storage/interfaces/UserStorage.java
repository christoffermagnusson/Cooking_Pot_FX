package storage.interfaces;

import domain.models.User;

public interface UserStorage {

	void storeUser(User user);

	User getUser(String username,String password);


}
