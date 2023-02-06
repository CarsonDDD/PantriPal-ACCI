package comp3350.acci.objects;

public class Contain {

    private Recipe recipe;

    private Ingredient ingredient;

    private double quantity;

    public Contain(Recipe recipe, Ingredient ingredient, double quantity){
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Recipe getRecipe(){
        return this.recipe;
    }

    public Ingredient getIngredient(){
        return this.ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public String toString(){
        return String.format("Contain: {%s, %s, quantity: %f}", recipe.toString(), ingredient.toString(), quantity);
    }

}
