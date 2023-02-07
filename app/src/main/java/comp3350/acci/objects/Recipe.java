package comp3350.acci.objects;


import java.util.Objects;

public class Recipe
{

	private static int count = 0;

	private User author;

	private int recipeID;

	private String instructions;

	private String name;

	private boolean isPrivate;

	private String difficulty;

	public Recipe(){
		recipeID = count;
		name = null;
		author = null;
		instructions = null;
		isPrivate = true;
		difficulty= null;
		count++;
	}

	public Recipe(User author, String name, String instructions, boolean isPrivate, String difficulty){
		this.recipeID = count;
		this.name = name;
		this.author = author;
		this.instructions = instructions;
		this.isPrivate = isPrivate;
		this.difficulty= difficulty;
		count++;
	}

	public Recipe(User author, int recipeID, String name, String instructions, boolean isPrivate, String difficulty){
		this.recipeID = recipeID;
		this.name = name;
		this.author = author;
		this.instructions = instructions;
		this.isPrivate = isPrivate;
		this.difficulty= difficulty;
		count++;
	}

	public int getRecipeID() {
		return recipeID;
	}

	public String getName() {
		return name;
	}

	public User getAuthor() {
		return author;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public String getInstructions() {
		return instructions;
	}

	public boolean getIsPrivate() {
		return isPrivate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public void setIsPrivate(boolean aPrivate) {
		isPrivate = aPrivate;
	}

	public boolean equals(Object other) {
		boolean equals = false;

		if (other instanceof Recipe)
		{
			final Recipe otherRecipe = (Recipe) other;
			equals = Objects.equals(this.recipeID, otherRecipe.recipeID);
		}

		return equals;
	}

	public String toString(){
		String str = String.format("recipe: {name: %s, author: %s, instructions: %s, isPrivate: %b, difficulty: %s}"
				, this.name, this.author.toString(), this.instructions, this.isPrivate, this.difficulty);
		return str;
	}

}