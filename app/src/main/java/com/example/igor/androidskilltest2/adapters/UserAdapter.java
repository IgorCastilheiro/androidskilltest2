package com.example.igor.androidskilltest2.adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.androidskilltest2.R;
import com.example.igor.androidskilltest2.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {
    private List<User> userList;
    private List<User> originalUserList;
    private OnItemClickListener itemClickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserAdapter() {
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void serUsers(List<User> users) {
        userList = users;
        originalUserList = users;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item_list, parent, false);

        return new UserViewHolder(v, itemClickListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userName.setText(user.getFirstName());
        ViewCompat.setTransitionName(holder.userAvatar, user.getFirstName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (userList != null)
            return userList.size();
        else return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userList = (List<User>) results.values;
                UserAdapter.this.notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<User> results = new ArrayList<>();
                if (originalUserList == null)
                    originalUserList = userList;
                if (constraint != null) {
                    if (originalUserList != null && originalUserList.size() > 0) {
                        for (final User user : originalUserList) {
                            if (user.getFirstName().toLowerCase()
                                    .contains(constraint.toString().toLowerCase()))
                                results.add(user);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }
        };
    }


    public interface OnItemClickListener {
        void onItemClick(ImageView view, int position, User user);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView userName;
        ImageView userAvatar;

        OnItemClickListener itemClickListener;

        UserViewHolder(View v, OnItemClickListener itemClickListener) {
            super(v);
            this.itemClickListener = itemClickListener;
            userName = v.findViewById(R.id.txtName);
            userAvatar = v.findViewById(R.id.imgAvatar);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(userAvatar, getAdapterPosition(), userList.get(getAdapterPosition()));
            }
        }
    }


}
