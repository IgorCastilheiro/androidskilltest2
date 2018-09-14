package com.example.igor.androidskilltest2.adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.androidskilltest2.R;
import com.example.igor.androidskilltest2.models.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> albumList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlbumAdapter() {
    }

    public void setAlbumList(List<Album> list) {
        albumList = list;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item_list, parent, false);

        return new AlbumViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumTitle.setText(album.getTitle());
        ViewCompat.setTransitionName(holder.albumThumb, album.getTitle());
        Picasso.get().load(album.getThumbnailUrl()).into(holder.albumThumb);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (albumList != null)
            return albumList.size();
        else return 0;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class AlbumViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView albumTitle;
        ImageView albumThumb;

        AlbumViewHolder(View v) {
            super(v);
            albumTitle = v.findViewById(R.id.txtName);
            albumThumb = v.findViewById(R.id.imgThumb);
        }

    }
}
