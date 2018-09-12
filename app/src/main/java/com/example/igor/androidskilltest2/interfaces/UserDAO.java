package com.example.igor.androidskilltest2.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.igor.androidskilltest2.models.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * from user ORDER BY first_name ASC")
    LiveData<List<User>> getAllUsers();
}
