package comp3350.acci.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import comp3350.acci.R;
import comp3350.acci.objects.Pantry;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryCardViewHolder> {
    private List<Pantry> pantries;

    public PantryAdapter(List<Pantry> pantries){
        this.pantries = pantries;
    }

    public class PantryCardViewHolder extends RecyclerView.ViewHolder{
        CardView pantry_card;
        TextView tv_ingredient;
        TextView tv_quantity;
        ImageView iv_edit;

        public PantryCardViewHolder(View itemView){
            super(itemView);

            pantry_card = itemView.findViewById(R.id.pantry_card);
            tv_ingredient = itemView.findViewById(R.id.tv_ingredient);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            iv_edit = itemView.findViewById(R.id.iv_edit);
        }

        public TextView getTv_ingredient(){
            return tv_ingredient;
        }

        public TextView getTv_quantity(){
            return tv_quantity;
        }
    }
    @Override
    public PantryCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        return new PantryCardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pantry_card, viewGroup, false));
    }
    @Override
    public void onBindViewHolder(PantryCardViewHolder viewHolder, final int position){
        viewHolder.getTv_ingredient().setText(pantries.get(position).getIngredient().getName());
        viewHolder.getTv_quantity().setText(Double.toString(pantries.get(position).getQuantity()));
    }

    @Override
    public int getItemCount(){
        return pantries.size();
    }
}