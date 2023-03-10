package comp3350.acci.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.acci.R;
import comp3350.acci.business.listeners.RecipeClickListener;
import comp3350.acci.objects.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeCardViewHolder>{
    //Context context;
    private List<Recipe> recipes;
    private RecipeClickListener listener;
    private int cardID;

    public RecipeAdapter(/*Context context, */int cardID, List<Recipe> list,RecipeClickListener listener) {
        //this.context = context;
        this.recipes = list;
        this.listener= listener;
        this.cardID = cardID;
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(cardID, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardViewHolder holder, int position) {
        holder.textView_title.setText(recipes.get(position).getName());
        //holder.textView_favorites.setText("3");
        //holder.textView_author.setText(list.get(position).getAuthor().getUserName());
        //holder.textView_instructions.setText(list.get(position).getInstructions());
        //holder.textView_difficulty.setText(list.get(position).getDifficulty());
        //holder.imageView_food.setImageBitmap();

        // Do not treat position as fixed; only use immediately and call holder.getAdapterPosition() to look it up later
        holder.recipe_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This may be getBindingAdapterPosition as holder.getAdapterPosition() is depreciated
                listener.onRecipeClick(recipes.get(holder.getAbsoluteAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}

class RecipeCardViewHolder extends RecyclerView.ViewHolder{

    CardView recipe_container;
    TextView textView_title;
    //TextView textView_instructions;
    TextView textView_difficulty;
    ImageView imageView_food;
    //TextView textView_favorites;
    //TextView textView_author;

    public RecipeCardViewHolder(@NonNull View itemView) {
        super(itemView);

        recipe_container = itemView.findViewById(R.id.recipe_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        imageView_food = itemView.findViewById(R.id.imageView_food);


    }
}