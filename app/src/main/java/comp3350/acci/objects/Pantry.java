package comp3350.acci.objects;

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

    public String toString(){
        return String.format("Pantry: {%s, %s, quantity: %f}", user.toString(), ingredient.toString(), quantity);
    }

}
