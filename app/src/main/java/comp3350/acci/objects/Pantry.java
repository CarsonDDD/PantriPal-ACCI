package comp3350.acci.objects;

import java.util.Objects;

public class Pantry {

    private User user;

    private Ingredient ingredient;

    private double quantity;

    private String unit;

    public Pantry(User user, Ingredient ingredient, double quantity){
        this.user = user;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = "";
    }

    public Pantry(User user, Ingredient ingredient, double quantity, String unit){
        this.user = user;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public User getUser(){
        return this.user;
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

    public void setUnit(String unit){
        this.unit = unit;
    }

    public boolean equals(Object other) {
        boolean equals = false;

        if (other instanceof Pantry)
        {
            final Pantry otherPantry = (Pantry) other;
            equals = Objects.equals(this.user, otherPantry.getUser()) && Objects.equals(this.ingredient, otherPantry.getIngredient());
        }

        return equals;
    }

    public String toString(){
        return String.format("Pantry: {%s, %s, quantity: %f}", user.toString(), ingredient.toString(), quantity);
    }

}
