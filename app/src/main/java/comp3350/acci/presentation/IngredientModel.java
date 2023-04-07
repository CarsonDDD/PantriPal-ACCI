package comp3350.acci.presentation;

public class IngredientModel {
    int img;
    String name, quantity;

    public IngredientModel(int image, String name, String quantity)
    {
        this.name = name;
        this.quantity = quantity;
        img = image;
    }

    public IngredientModel(String name, String quantity){
        this.quantity= quantity;
        this.name = name;
    }


}
