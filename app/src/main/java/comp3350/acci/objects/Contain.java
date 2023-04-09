package comp3350.acci.objects;

import java.util.Objects;

public class Contain {

    private Recipe recipe;

    private Ingredient ingredient;

    private double quantity;

    private String unit;

    public Contain(Recipe recipe, Ingredient ingredient, double quantity){
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = "";
    }

    public Contain(Recipe recipe, Ingredient ingredient, double quantity, String unit){
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object other) {
        boolean equals = false;

        if (other instanceof Contain)
        {
            final Contain otherContain = (Contain) other;
            equals = Objects.equals(this.recipe, otherContain.getRecipe()) && Objects.equals(this.ingredient, otherContain.getIngredient());
        }

        return equals;
    }

    public String toString(){
        return String.format("Contain: {%s, %s, quantity: %f, unit: %s}", recipe.toString(), ingredient.toString(), quantity, unit);
    }

}
