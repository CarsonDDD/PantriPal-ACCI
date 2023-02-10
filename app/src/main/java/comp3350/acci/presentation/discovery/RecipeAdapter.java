package comp3350.acci.presentation.discovery;

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
import comp3350.acci.objects.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeCardViewHolder>{

    Context context;
    List<Recipe> list;

    public RecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeCardViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardViewHolder holder, int position) {
        holder.textView_title.setText("Title");
        holder.textView_favorites.setText("3");
        //holder.imageView_food.setImageBitmap();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecipeCardViewHolder extends RecyclerView.ViewHolder{

    CardView recipe_container;
    TextView textView_title;
    ImageView imageView_food;
    TextView textView_favorites;
    TextView textView_author;

    public RecipeCardViewHolder(@NonNull View itemView) {
        super(itemView);

        recipe_container = itemView.findViewById(R.id.recipe_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        imageView_food = itemView.findViewById(R.id.imageView_food);
        textView_author = itemView.findViewById(R.id.textView_author);
        textView_favorites = itemView.findViewById(R.id.textView_favorites);
    }
}