package domain.models;

public class Session {

	private Chef chef;
	private Recipe recipe;
	private User user;

	public Session(User user){
		this.user=user;
		this.chef=user.getChef();
	}

	public void setChef(Chef chef){
		this.chef=chef;
	}
	public Chef getChef(){
		return chef;
	}
	public void setCurrentRecipe(Recipe recipe){
		this.recipe=recipe;
	}
	public Recipe getCurrentRecipe(){
		return recipe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
