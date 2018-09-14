package com.example.igor.androidskilltest2.interfaces;

import com.example.igor.androidskilltest2.models.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumAPI {

    @GET("photos/")
    Call<List<Album>> loadAlbums();
}
