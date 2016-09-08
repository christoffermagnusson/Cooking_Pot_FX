package domain.models;

public class User {

	private String username;
	private String password;
	private Chef chef;

	public User(String username, String password){
		this.username=username;
		this.password=password;
	}
	public Chef getChef(){
		return this.chef;
	}
	public void setChef(Chef chef){
		this.chef=chef;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String toString(){
		return String.format("%s || %s"
				,username
				,password);
	}


}
