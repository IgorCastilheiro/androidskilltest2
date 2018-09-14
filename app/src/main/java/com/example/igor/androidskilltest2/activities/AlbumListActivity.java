package com.example.igor.androidskilltest2.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.example.igor.androidskilltest2.R;
import com.example.igor.androidskilltest2.adapters.AlbumAdapter;
import com.example.igor.androidskilltest2.interfaces.AlbumAPI;
import com.example.igor.androidskilltest2.models.Album;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumListActivity extends AppCompatActivity {

    private AlbumAdapter albumAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        recyclerView = findViewById(R.id.albumListView);
        albumAdapter = new AlbumAdapter();
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.album_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlbumAPI albumAPI = retrofit.create(AlbumAPI.class);

        Call<List<Album>> call = albumAPI.loadAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    albumAdapter.setAlbumList(response.body());
                } else showError();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                showError();
            }
        });
    }

    private int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 158);
    }

    private void showError() {
        Snackbar.make(recyclerView, getString(R.string.generic_error), Snackbar.LENGTH_LONG).show();
    }
}
