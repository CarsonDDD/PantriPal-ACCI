package comp3350.acci.objects;

public class Pantry {

    private User user;

    private Ingredient ingredient;

    private double quantity;

    public Pantry(User user, Ingredient ingredient, double quantity){
        this.user = user;
        this.ingredient = ingredient;
        this.quantity = quantity;
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

    public String toString(){
        return String.format("Pantry: {%s, %s, quantity: %f}", user.toString(), ingredient.toString(), quantity);
    }

}
