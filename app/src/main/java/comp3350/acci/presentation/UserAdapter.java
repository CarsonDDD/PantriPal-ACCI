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
import comp3350.acci.objects.User;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    //Context context;
    private List<User> users;
    private UserClickListener listener;

    public interface UserClickListener {
        void onClick(User user);
    }

    public UserAdapter(List<User> users, UserClickListener onClick) {
        this.users = users;
        this.listener = onClick;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tv_name.setText(users.get(position).getUserName());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(users.get(holder.getAbsoluteAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder{
    CardView container;
    TextView tv_name;
    //ImageView iv_picture;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
        container = itemView.findViewById(R.id.user_container);
    }
}