package comp3350.acci.objects;


import java.util.Objects;

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

	public boolean equals(Object other) {
		boolean equals = false;

		if (other instanceof Ingredient)
		{
			final Ingredient otherIngredient = (Ingredient) other;
			equals = Objects.equals(this.name, otherIngredient.getName());
		}

		return equals;
	}

	public String toString(){
		return "ingredient: {name: " + name + "}";
	}

}