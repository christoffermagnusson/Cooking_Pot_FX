package domain.models;

public class Session {

	private Chef chef;
	private Recipe recipe;

	public Session(Chef chef){
		this.chef=chef;
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

}
