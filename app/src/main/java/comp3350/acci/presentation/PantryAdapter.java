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
import comp3350.acci.business.listeners.PantryEditClickListener;
import comp3350.acci.objects.Pantry;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryCardViewHolder> {
    private List<Pantry> pantries;

    private PantryEditClickListener listener;

    public PantryAdapter(List<Pantry> pantries, PantryEditClickListener listener){
        this.pantries = pantries;
        this.listener = listener;
    }

    public class PantryCardViewHolder extends RecyclerView.ViewHolder{
        CardView pantry_card;
        TextView tv_ingredient;
        TextView tv_quantity;

        TextView tv_unit;
        ImageView iv_edit;

        public PantryCardViewHolder(View itemView){
            super(itemView);

            pantry_card = itemView.findViewById(R.id.pantry_card);
            tv_ingredient = itemView.findViewById(R.id.tv_ingredient);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_unit = itemView.findViewById(R.id.tv_unit);
            iv_edit = itemView.findViewById(R.id.iv_edit);

            iv_edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    listener.onPantryEditClick(pantries.get(getAbsoluteAdapterPosition()));
                }
            });
        }

        public TextView getTv_ingredient(){
            return tv_ingredient;
        }

        public TextView getTv_quantity(){
            return tv_quantity;
        }

        public TextView getTv_Unit(){
            return tv_unit;
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
        viewHolder.getTv_Unit().setText(pantries.get(position).getUnit());
    }

    @Override
    public int getItemCount(){
        return pantries.size();
    }
}