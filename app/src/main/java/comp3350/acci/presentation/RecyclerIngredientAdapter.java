package comp3350.acci.presentation;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import comp3350.acci.R;


public class RecyclerIngredientAdapter extends RecyclerView.Adapter<RecyclerIngredientAdapter.ViewHolder> {
    Context context;
    ArrayList<IngredientModel> arrIngredients;
    public RecyclerIngredientAdapter(Context context, ArrayList<IngredientModel> arrayIngredients){
        this.context= context;
        arrIngredients = arrayIngredients;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ingredient_row, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientModel model = arrIngredients.get(position);
       // holder.imgIngredient.setImageResource(arrIngredients.get(position).img);
        holder.txtName.setText(arrIngredients.get(position).name);
        holder.txtquantity.setText(arrIngredients.get(position).quantity);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog= new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_item);
                EditText editName = dialog.findViewById(R.id.edtIngredient);
                EditText editquantity = dialog.findViewById(R.id.et_quantity);
                Button btnAction = dialog.findViewById(R.id.btnAction);
                TextView txtTitle = dialog.findViewById(R.id.txtName);
                txtTitle.setText("Update Ingredient");
                btnAction.setText("Update");
                editName.setText(arrIngredients.get(holder.getAbsoluteAdapterPosition()).name);
                editquantity.setText(arrIngredients.get(holder.getAbsoluteAdapterPosition()).quantity);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", quantity = "";
                        if (!editName.getText().toString().equals("")) {
                            name = editName.getText().toString();
                        } else {
                            Toast.makeText(context, "Please enter Ingredient Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!editquantity.getText().toString().equals("")) {
                            quantity = editquantity.getText().toString();
                        } else {
                            Toast.makeText(context, "Please enter Ingredient quantity", Toast.LENGTH_SHORT).show();
                        }

                        arrIngredients.set(holder.getAbsoluteAdapterPosition(), new IngredientModel(name, quantity));
                        notifyItemChanged(holder.getAbsoluteAdapterPosition());

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtquantity;
        ImageView imgIngredient;
        LinearLayout llRow;
        public ViewHolder(View itemView)
        {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtquantity = itemView.findViewById(R.id.txtquantity);
            llRow = itemView.findViewById(R.id.llrow);

        }
    }

}
