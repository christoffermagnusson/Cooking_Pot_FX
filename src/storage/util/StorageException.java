package storage.util;

public class StorageException extends Exception {

	public StorageException(Exception e){
		super(e);
	}
	public StorageException(String msg){
		super(msg);
	}
}
