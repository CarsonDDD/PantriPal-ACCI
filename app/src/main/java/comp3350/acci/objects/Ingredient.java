package comp3350.acci.objects;


public class Ingredient
{

	private String name;

	public Ingredient(){
		name = null;
	}

	public Ingredient(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString(){
		return "ingredient: {name: " + name + "}";
	}

}