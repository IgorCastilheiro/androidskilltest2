package com.example.igor.androidskilltest2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.igor.androidskilltest2.interfaces.UserDAO;
import com.example.igor.androidskilltest2.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDAO userDAO();
}
