package co.com.ceiba.mobile.pruebadeingreso.view.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.User;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private Context context;

    private UserSelectedClick userSelectedClick;

    private List<User> userList;


    public UserListAdapter(Context context, List<User> userList, UserSelectedClick userSelectedClick) {
        this.context = context;
        this.userList = userList;
        this.userSelectedClick = userSelectedClick;
    }

    public void filterList(List<User> filteredList) {
        this.userList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        UserListItemBinding binding = UserListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textViewName.setText(userList.get(position).getName());
        viewHolder.textViewPhone.setText(userList.get(position).getPhone());
        viewHolder.textViewEmail.setText(userList.get(position).getEmail());
        viewHolder.btnViewPost.setOnClickListener(view -> userSelectedClick.userSelectedOnClickListener(userList.get(position)));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;

        TextView textViewPhone;

        TextView textViewEmail;

        Button btnViewPost;

        ViewHolder(final UserListItemBinding itemView) {
            super(itemView.getRoot());
            this.textViewName = itemView.name;
            this.textViewPhone = itemView.phone;
            this.textViewEmail = itemView.email;
            this.btnViewPost = itemView.btnViewPost;
        }
    }
}
