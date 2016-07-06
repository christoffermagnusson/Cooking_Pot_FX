package domain.models;

public class Session {

	private Chef chef;

	public Session(Chef chef){
		this.chef=chef;
	}

	public void setChef(Chef chef){
		this.chef=chef;
	}
	public Chef getChef(){
		return chef;
	}

}
